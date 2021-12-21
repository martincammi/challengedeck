package challengedeck.players

import challengedeck.Deck
import challengedeck.DualGame
import challengedeck.cards.Card
import challengedeck.cards.Player
import challengedeck.cards.Strategy
import challengedeck.decks.basicdeck.WarriorDeck
import challengedeck.strategies.AlwaysAttackPlayerStrategy
import grails.util.Pair

class BasicPlayer implements Player {

    String name = "Hans"
    Deck deck = new WarriorDeck()
    List<Card> hand = new ArrayList()
    Strategy strategy = new AlwaysAttackPlayerStrategy()

    BasicPlayer(){
    }

    static constraints = {
    }

    @Override
    Boolean needMulligan() {
        return null
    }

    Pair<Card, List<Card>> wantToPlaySpell(DualGame dualGame){
        throw new Exception("wantToPlaySpell method is not implemented for class BasicPlayer")
    }
}
