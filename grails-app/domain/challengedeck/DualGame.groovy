package challengedeck

import challengedeck.abilities.Ability
import challengedeck.cards.Card
import challengedeck.cards.Land
import challengedeck.cards.Player
import challengedeck.cards.Strategy
import challengedeck.players.DeckPlayer
import challengedeck.state.phases.BeginningPhase
import challengedeck.state.phases.StepState
import challengedeck.state.steps.InitialStep
import challengedeck.strategies.AlwaysAttackCreaturesStrategy
import grails.util.Pair

class DualGame {

    public static int DEFAULT_INITIAL_LIFE = 20
    public static int DEFAULT_INITIAL_CARDS = 7
    public static int NO_TURNS = -1

    Player player1
    Player player2
    private int playerLife1
    private int playerLife2
    private int player1Turn = 0
    private int player2Turn = 0
    private Player activePlayer
    private Player nonActivePlayer
    Boolean winner
    List<Card> battlefield = new ArrayList<>()
    private Map<Trigger, Ability> stepsListeners = new HashMap<Trigger, Ability>()
    StepState stepState
    Stack<Card> stack = new Stack<Card>()
    int turnCount = 0
    Boolean player1LandDrop = false
    Boolean player2LandDrop = false

    static void main(String[] args){
        println "Beginning Dual Game"
        DualGame dualGame = DualGame.getInstance()
        dualGame.start()
    }

    static DualGame getInstance(){

        Deck deck1 = new Deck()
        Deck deck2 = new Deck()

        Strategy strategy1 = new AlwaysAttackCreaturesStrategy()
        Strategy strategy2 = new AlwaysAttackCreaturesStrategy()

        Player player1 = new DeckPlayer("Robert", deck1, strategy1)
        Player player2 = new DeckPlayer("Alice", deck2, strategy2)

        DualGame dualGame = new DualGame(player1, player2)
        return dualGame
    }

    DualGame(Player player1, Player player2){
        this.playerLife1 = DEFAULT_INITIAL_LIFE
        this.playerLife2 = DEFAULT_INITIAL_LIFE
        this.player1 = player1
        this.player2 = player2
        this.winner = false
    }

    void start(def turns = 20){

        Boolean turnSafeCondition = (turnCount <= turns)
        activePlayer = player1
        nonActivePlayer = player2
        stepState = InitialStep.getInstance()

        eachPlayerDrawsCards()
        takeMulligans()
        println ""
        println "--- " + activePlayer.name + " (active player) --- "
        while(!winner && turnSafeCondition){

            println "Turn Count: " + turnCount
            //Begin Turn
                //Untap step
                stepState.next(this)
                playersGetPriority()

                //Upkeep step
                stepState.next(this)
                playersGetPriority()

                //Draw step
                stepState.next(this)
                playersGetPriority()
                drawCard(activePlayer)

                //Main phase 1 step
                stepState.next(this)
                playersGetPriority()

                //Beginning Combat step
                stepState.next(this)
                playersGetPriority()

                //Declare Attackers step
                stepState.next(this)
                playersGetPriority()

                //Declare Blockers step
                stepState.next(this)
                playersGetPriority()

                //Assign Combat Damage step
                stepState.next(this)
                playersGetPriority()

                //End of Combat step
                stepState.next(this)
                playersGetPriority()

                //Main phase 2 step
                stepState.next(this)
                playersGetPriority()

                //End step
                stepState.next(this)
                playersGetPriority()

                //Cleanup step
                stepState.next(this)
                playersGetPriority()

            //End turn
            changeActivePlayer()
            println ""
            println "--- " + activePlayer.name + " (active player) --- "

            //Update safe condition
            turnSafeCondition = (turnCount <= turns)
            turnCount++
        }

        if(turnSafeCondition == false && !winner){
            println "--- Game ended by turn safe condition ---"
        }

    }

    @Deprecated
    void upkeep() {


        //List<Notifiable> notifiables = stepsNotify.get(Trigger.BEGINNING_UPKEEP)

        notifiables.each {
            it.notify()
        }

        //Creatures from last turn lose summonsickness

        resolveStack()
    }


    List<Card> getCardsFromPlayer(Player player, CardType cardType){
        return battlefield.findAll { it.owner.equals(player) && it.cardTypes.contains(cardType) }
    }

    void setStepState(StepState stepState){
        this.stepState = stepState

        List<Ability> abilities
        List<Trigger> triggers = stepState.getTriggers()

        //For each trigger that triggers on this step..
        triggers.each { trigger ->
            abilities = stepsListeners.get(Trigger.BEGINNING_UPKEEP)
            //..add all of the abilities to the stack
            abilities.each { ability ->
                stack.add(ability.action)
            }
        }
    }

    /**
     * 117.3d If a player has priority and chooses not to take any actions, that player passes. If any mana is in that player’s mana pool, they announce what
     * mana is there. Then the next player in turn order receives priority.
     */
    void playersGetPriority(){

        Pair playInfo1
        Pair playInfo2
        Boolean anythingToPlay = true
        int counter = 0
        //Pending check play land scenario

        while(anythingToPlay){

            while((playInfo1 = activePlayer.wantToPlaySpell(this)) != null){
                playCard(playInfo1.aValue, activePlayer)
                tapCards(playInfo1.bValue)
            }

            while((playInfo2 = nonActivePlayer.wantToPlaySpell(this)) != null){
                playCard(playInfo2.aValue, nonActivePlayer)
                tapCards(playInfo2.bValue)
            }

            anythingToPlay = playInfo2 != null

            counter++
            infiniteCheck(counter)
        }

    }

    void infiniteCheck(int a){
        if(a == 100){
            println " --- INFINITE LOOP DETECTED ---"
        }
    }

    /**
     * Put a Land onto the battlefield or cast a spell
     * @param card
     */
    void playCard(Card card, Player player){
        if(card instanceof Land){
            battlefield.add(card)
            playerDropLand()
            println "\t" + player.name + " played a " + card.name + " (" + player.hand.size() + " cards in hand)"
        }else{
            stack.push(card)
            println "\t" + player.name + " cast a " + card.name + " (" + player.hand.size() + " cards in hand)"
        }
        player.getHand().remove(card)
    }

    void tapCards(List<Card> cardsToTap){
        cardsToTap.each { cardToTap ->
            cardToTap.tap()
        }
    }

    Boolean hasDropLand(){
        if(activePlayer.equals(player1)){
            return player1LandDrop
        }else{
            return player2LandDrop
        }
    }

    void playerDropLand(){

        if(activePlayer.equals(player1)){
            player1LandDrop = true
        }else{
            player2LandDrop = true
        }
    }

    /**
     * Changes the active player
     */
    void changeActivePlayer(){
        if(activePlayer.equals(player1)){
            activePlayer = player2
            nonActivePlayer = player1
            player2LandDrop = false
        }else{
            activePlayer = player1
            nonActivePlayer = player2
            player1LandDrop = false
        }
    }

    void eachPlayerDrawsCards(){
        startHandForPlayer(player1)
        startHandForPlayer(player2)
    }

    void takeMulligans(){
        boolean needMulligan1 = needMulligan(player1)
        boolean needMulligan2 = needMulligan(player2)
        boolean stopMulligan1 = false
        boolean stopMulligan2 = false

        while(needMulligan1 || needMulligan2){
            if(needMulligan1){
                println ""
                println "--- " + player1.getName() + " takes mulligan of " + (player1.hand.size() - 1) + " cards ---"
                mulligan(player1, player1.hand.size() - 1)
            }
            if(needMulligan2){
                println ""
                println "--- " + player2.getName() + " takes mulligan of " + (player2.hand.size() - 1) + " cards ---"
                mulligan(player2, player2.hand.size() - 1)
            }

            needMulligan1 = needMulligan(player1)
            if(!stopMulligan1 && !needMulligan1){
                println ""
                println "--- " + player1.name + " stays with " + player1.hand.size() + " cards ---"
                stopMulligan1 = true
            }

            needMulligan2 = needMulligan(player2)
            if(!stopMulligan2 && !needMulligan2){
                println ""
                println "--- " + player2.name + " stays with " + player2.hand.size() + " cards ---"
                stopMulligan2 = true
            }
        }
    }

    void startHandForPlayer(Player player, int cardsToDraw = 7){
        mulligan(player, cardsToDraw)
    }

    void mulligan(Player player, int cardsToDraw){
        player.deck.add(player.hand)
        player.hand.clear()
        println ""
        println "--- " + player.getName() + " draws " + cardsToDraw + " cards ---"
        for (int i = 1; i <= cardsToDraw; i++) {
            Card card = drawCard(player)
        }
    }

    Card drawCard(Player player){
        Card card = player.deck.draw()
        player.hand.add(card)
        println "\t" + player.name + " draws a card " + card.name + " (" + player.hand.size() + " cards in hand)"
        return card
    }

    Boolean needMulligan(Player player){
        player.needMulligan()
    }

}
