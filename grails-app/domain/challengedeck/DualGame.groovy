package challengedeck

import challengedeck.abilities.Ability
import challengedeck.cards.Card
import challengedeck.cards.Land
import challengedeck.cards.Player
import challengedeck.cards.Strategy
import challengedeck.players.DeckPlayer
import challengedeck.state.phases.BeginningPhase
import challengedeck.state.phases.StepState
import challengedeck.strategies.AlwaysAttackCreaturesStrategy
import grails.util.Pair

class DualGame {

    public static int DEFAULT_INITIAL_LIFE = 20
    public static int DEFAULT_INITIAL_CARDS = 7
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

        int turnCount = 0
        Boolean turnSafeCondition = true
        activePlayer = player1
        nonActivePlayer = player2

        eachPlayerDrawsCards()
        takeMulligans()

        while(!winner && turnSafeCondition){

            //Begin Turn
            setStepState(BeginningPhase.getInstance().getStepState())
            playersGetPriority()

            //End turn
            changeActivePlayer()

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
        return battlefield.collect { it.owner.equals(player) && it.cardTypes.contains(cardType) }
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

        //Pending check play land scenario

        while(anythingToPlay){

            anythingToPlay = false
            while((playInfo1 = activePlayer.wantToPlaySpell(this)) != null){
                playCard(playInfo1.aValue)
                tapCards(playInfo1.bValue)
                anythingToPlay = true
            }

            while((playInfo2 = nonActivePlayer.wantToPlaySpell(this)) != null){
                playCard(playInfo2.aValue)
                tapCards(playInfo2.bValue)
                anythingToPlay = true
            }

        }

    }

    void playCard(Card card){
        if(card instanceof Land){
            battlefield.add(card)
        }else{
            stack.push(card)
        }
    }

    void tapCards(List<Card> cardsToTap){
        cardsToTap.each { cardToTap ->
            cardToTap.tap()
        }
    }

    /**
     * Changes the active player
     */
    void changeActivePlayer(){
        if(activePlayer.equals(player1)){
            activePlayer = player2
            nonActivePlayer = player1
        }else{
            activePlayer = player1
            nonActivePlayer = player2
        }
    }

    void eachPlayerDrawsCards(){
        startHandForPlayer(player1)
        startHandForPlayer(player2)
    }

    void takeMulligans(){
        boolean needMulligan1 = needMulligan(player1)
        boolean needMulligan2 = needMulligan(player2)

        while(needMulligan1 || needMulligan1){
            if(needMulligan1){
                mulligan(player1, player1.hand.size() - 1)
            }
            if(needMulligan2){
                mulligan(player2, player2.hand.size() - 1)
            }

            needMulligan1 = needMulligan(player1)
            needMulligan2 = needMulligan(player2)
        }
    }

    void startHandForPlayer(Player player, int cardsToDraw = 7){
        mulligan(player, cardsToDraw)
    }

    void mulligan(Player player, int cardsToDraw){
        player.deck.add(player.hand)
        player.hand.clear()
        println "--- " + player.getName() + " draws " + cardsToDraw + " cards ---"
        for (int i = 1; i <= cardsToDraw; i++) {
            Card card = drawCard(player)
            println "(" + player.deck.name +  " draw card " + card.name + ")"
        }
    }

    Card drawCard(Player player){
        Card card = player.deck.draw()
        player.hand.add(card)
        return card
    }

    Boolean needMulligan(Player player){
        player.needMulligan()
    }

}
