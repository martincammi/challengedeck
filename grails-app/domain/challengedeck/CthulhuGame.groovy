package challengedeck

import challengedeck.abilities.Ability
import challengedeck.abilities.Expire
import challengedeck.abilities.Haste
import challengedeck.cards.Card
import challengedeck.cards.Creature
import challengedeck.cards.Permanent
import challengedeck.cards.Player
import challengedeck.cards.Sorcery
import challengedeck.decks.basicdeck.WarriorDeck
import challengedeck.decks.cthulhudeck.CthulhuDeck
import challengedeck.exceptions.CthuluLostGameException
import challengedeck.exceptions.NoCardsInLibraryException
import challengedeck.exceptions.PlayerLostGameException

class CthulhuGame {

    Integer playerLife //playerLife will be always shared
    Deck cthulhuDeck
    Player player //To be extended to many players in the future
    List<Card> battlefield = new ArrayList<>()
    List<Card> exile = new ArrayList<>()
    List<Card> graveyard = new ArrayList<>()
    List<Card> playerGraveyard = new ArrayList<>()
    List<Action> stack = new ArrayList<>()
    List<Ability> stepsAbilities = new ArrayList<>()
    Boolean cthuluWin = false
    Boolean playerWin = false
    Integer cthulhuTurn = 1
    Integer playerTurn = 1
    String activePlayer
    Boolean single = false //For testing purposes for Cthulhu to play alone


    static CthulhuGame getSingleGameInstance(){
        CthulhuGame game = new CthulhuGame()
        game.single = true
        return game
    }

    static void main(String[] args){
        println "Beginning Confront Cthulu"

        CthulhuGame cthulhuGame = new CthulhuGame()
        cthulhuGame.start()
    }

    CthulhuGame(){
        this.single = single
        this.playerLife = 20
        initCthulhuDeck()
        this.player = new BasicPlayer()
    }

    private void initCthulhuDeck(){
        cthulhuDeck = new CthulhuDeck()
    }

    void start(def turns){

        if(!turns){ turns = 100 }

        playerInitial()

        while(!cthuluWin && !playerWin && cthulhuTurn <= turns){

            try {
                activePlayer = CthulhuDeck.CTHULHU_NAME
                println ""// "----------------"
                println "CTHULHU TURN " + cthulhuTurn
                upkeep()
                cthulhuCast(2)
                cthulhuMainPhase()
                combat()
                cthulhuAttack()
                endStep()

                println "Player life: " + playerLife
                println "Cthulhu life: " + cthulhuDeck.size()
                print "Battlefield: "
                println battlefield.collect { c -> c.string() }.join(", ")
                print "Exile: "
                println exile.collect { c -> c.string() }.join(", ")
                cthulhuTurn++

                if(!single){
                    // ----------------------
                    activePlayer = WarriorDeck.newInstance().name
                    println ""// "----------------"
                    println "PLAYER TURN " + playerTurn
                    upkeep()
                    playerCast(1)
                    playerMainPhase()
                    combat()
                    playerAttack()
                    endStep()

                    println "Player life: " + playerLife
                    println "Cthulhu life: " + cthulhuDeck.size()
                    println "Player hand: " + player.hand.size()
                    print "Battlefield: "
                    println battlefield.collect { c -> c.string() }.join(", ")
                    print "Exile: "
                    println exile.collect { c -> c.string() }.join(", ")
                    playerTurn++
                }

            }catch(NoCardsInLibraryException e){
                if(!battlefield.find{ c -> c.cardSubTypes.contains(CardSubType.OCTOPUS)}){
                    playerWin = true
                }
            }catch(PlayerLostGameException e){
                println "Player with " + playerLife + " life"
                cthuluWin = true
            }catch(CthuluLostGameException e){
                println e.message + " " + cthulhuDeck.size() + " life"
                cthuluWin = true
            }
        }

        if(playerWin){
            println "Congratulations, you have defeated Cthulhu!! on turn " + playerTurn
        }
        if(cthuluWin){
            println "You mortal, have succumbed to the arcane power of Cthulhu!! on turn " + cthulhuTurn
        }
    }

    void upkeep() {

        //Creatures from last turn lose summonsickness
        battlefield.findAll { c -> c instanceof Creature && isActivePlayer(c.owner)}.each{  c -> c.hasSummoningSickness = false }
        triggersToStack(Trigger.BEGINNING_UPKEEP)
        resolveStack()
    }

    void playerInitial(){
        println "---PLAYER INITIAL ---"
        for (int i = 1; i <= 7; i++) {
            Card card = drawCard(player)
            println "(" + player.deck.name +  " draw card " + card.name + ")"
        }
    }

    void discardCard(Player player){
        if(player.hand.size() > 0){
            triggersToStack(Trigger.PLAYER_DISCARD_CARD)
            player.hand.removeAt(0)
            println "(" + player.deck.name + " hand size: " + player.hand.size() + ")"
        }else{
            println "(" + player.deck.name + " has zero cards in hand, no further discard)"
        }
    }

    Card drawCard(Player player){
        Card card = player.deck.draw()
        player.hand.add(card)
        return card
    }

    void cthulhuCast(Integer many){
        println "---CAST STEP ---"
        checkCthulhu()
        for (int i = 1; i <= many; i++) {
            Card card = cthulhuDeck.draw()
            println "Casting card " + card.name

            List abilities = card.abilities.findAll { a-> a.trigger == Trigger.RESOLVE}
            abilities.each { ability ->
                stack.add(ability.action.newInstance())
            }

            card.resolve(this)
            if(card instanceof Sorcery){
                graveyard.add(card)
            }
//        println "Casted card " + card
        }
    }

    void playerCast(Integer many){
        println "---CAST STEP ---"
        for (int i = 1; i <= many; i++) {
            Card card = drawCard(player)
            println "Casting card " + card.name

            List abilities = card.abilities.findAll { a-> a.trigger == Trigger.RESOLVE}
            abilities.each { ability ->
                stack.add(ability.action.newInstance())
            }

            card.resolve(this)
//        println "Casted card " + card
        }
    }

    void cthulhuMainPhase(){
        triggersToStack(Trigger.BEGINNING_MAIN_PHASE_1)
        resolveStack()
    }

    void playerMainPhase(){

    }

    void combat(){
        //println "---COMBAT STEP ---"
        triggersToStack(Trigger.BEGINNING_OF_COMBAT)
        resolveStack()
    }

    void cthulhuAttack(){
        println "---ATTACKING STEP ---"
        //Creatures that can attack
        List<Creature> creatures = battlefield.findAll { c ->
            c instanceof Creature && isActivePlayer(c.owner) && (!c.hasSummoningSickness || c.abilities*.class.contains(Haste.class))
        }

        //Check if effectively the creatures are obliged to attack.
        creatures = creatures.findAll { c -> c instanceof Creature && c.shouldAttack}

        creatures.each { creature ->
            println "Attacking creature " + creature.name
        }

        Integer damage = creatures*.power?.sum()?:0
        println "Total damage: " + damage
        substractPlayerLife(damage)
    }

    void playerAttack(){
        println "---ATTACKING STEP ---"
        //Creatures that can attack
        List<Creature> creatures = battlefield.findAll { c ->
            c instanceof Creature && isActivePlayer(c.owner) && (!c.hasSummoningSickness || c.abilities*.class.contains(Haste.class))
        }

        player.strategy.onAttack(this)

        //Check if effectively the creatures are obliged to attack.
        creatures = creatures.findAll { c -> c instanceof Creature && c.shouldAttack}

        creatures.each { creature ->
            println "Attacking creature " + creature.name
        }

        Integer damage = creatures*.power?.sum()?:0
        println "Total damage: " + damage
        millCthulhuDeck(damage)
    }

    void endStep(){
        println "---END STEP ---"

        //Remove all the abilities that have expiration at end of turn
        stepsAbilities.removeAll(stepsAbilities.findAll { a -> isActivePlayer(a.card.owner) && a.expire.equals(Expire.END_OF_TURN) })

        triggersToStack(Trigger.BEGINNING_END_STEP)
        resolveStack()

        battlefield.each{ card ->

            if(isActivePlayer(card.owner)){
                card.abilities.removeAll{ a -> a.expire.equals(Expire.END_OF_TURN)}

                if(card instanceof Creature){
                    card.shouldAttack = false
                }
            }
        }
    }

    void resolveStack(){
        while(!stack.isEmpty()){
            Action action = stack.last()
            stack.remove(action)
            action.resolve(this)
            checkCreatures()
            checkPlayers()
        }
    }

    void checkCreatures(){
        getAllCreatures().each { c ->
            if(c.getToughness() <= 0){
                leaveTheBattlefield(c)
            }
        }
    }

    void checkPlayers(){
        if(playerLife <= 0){
            throw new PlayerLostGameException()
        }
    }

    void checkCthulhu(){
        if(cthulhuDeck.size() <= 0){
            throw new CthuluLostGameException()
        }
    }

    void putOnBattlefield(Card card){
        //Put on battlefield
        battlefield.add(card)
        card.controller = card.owner

        //Static abilities take effect immediately, don't use the stack.
        stepsAbilities.each { a ->

            if(card instanceof Creature && a.trigger == Trigger.STATIC_CREATURE_ENTERS_THE_BATTLEFIELD){
                a.action.newInstance().resolve(this, card)
            }
        }

        //Check for trigger enter the battlefield
        List abilities = card.abilities
        abilities.each { a->
            if(a.trigger == Trigger.ENTER_THE_BATTLEFIELD){
                stack.add(a.action.newInstance())
            }else{
                if(a.trigger == Trigger.STATIC_CREATURE_ENTERS_THE_BATTLEFIELD) {
                    a.action.newInstance().resolve(this)
                }
                stepsAbilities.add(a)
            }
        }
    }

    void removeAllCounters(Card card){

        card.counters.clear()
        Ability ability = card.abilities.find { a-> a.trigger == Trigger.REMOVE_ALL_COUNTERS}
        if(ability){
            stack.add(ability.action.newInstance())
        }
    }

    void reveal(Card card){

        Ability ability = card.abilities.find { a-> a.trigger == Trigger.REVEAL}
        if(ability){
            stack.add(ability.action.newInstance())
        }
    }

    void leaveTheBattlefield(Permanent permanent){

        //Search for leave the battlefield abilities and add them to the stack.
        Ability ability = permanent.abilities.find { a-> a.trigger == Trigger.LEAVES_THE_BATTLEFIELD }
        if(ability){
            stack.add(ability.action.newInstance())
        }

        //Leave all the abilities of that card from game
        stepsAbilities.removeAll { a -> a.card.equals(permanent) }

        //Leave the battlefield
        battlefield.remove(permanent)
        if(permanent instanceof Creature) { permanent.reset() }
        graveyard.add(permanent)

        //Static abilities take effect immediately, don't use the stack.
        stepsAbilities.each { a ->
            if(a.trigger == Trigger.STATIC_CREATURE_LEAVES_THE_BATTLEFIELD){
                a.action.newInstance().resolve(this, permanent)
            }
        }

        println "Card leaving the battlefield " + permanent.name
    }

    void sacrifice(Permanent permanent){
        leaveTheBattlefield(permanent)
    }

    void sacrificeAll(List<Permanent> permanents){
        removeAllFromBattlefield(permanents)
    }

    private void removeAllFromBattlefield(List<Permanent> permanents){
        permanents.each { leaveTheBattlefield(it)}
    }

    static constraints = {
    }

    List<Creature> getAllCreatures(){
        return battlefield.findAll { c -> c instanceof Creature}
    }

    void addPlayerLife(Integer life) {
        this.playerLife += life
        println "(New player life: " + this.playerLife + ")"
    }

    void substractPlayerLife(Integer life) {
        this.playerLife -= life
        println "(New player life: " + this.playerLife + ")"
    }

    void millCthulhuDeck(Integer life) {

        life.times{
            Card  card = this.cthulhuDeck.draw()
            graveyard.add(card)
        }
        println "(New Cthulhu life: " + this.cthulhuDeck.size() + ")"
    }


    void triggersToStack(Trigger trigger){
        for (Ability ability : stepsAbilities){
            if(ability.card == null){
                print ability.class
            }
            if(isActivePlayer(ability.card.owner) && ability.trigger.equals(trigger)){
                stack.add(ability.action.newInstance())
            }
        }
    }

    boolean isActivePlayer(String owner){
        return activePlayer.equals(owner)
    }
}
