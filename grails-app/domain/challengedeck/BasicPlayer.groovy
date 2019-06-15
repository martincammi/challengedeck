package challengedeck

import challengedeck.cards.Card
import challengedeck.cards.Player
import challengedeck.cards.Strategy
import challengedeck.decks.basicdeck.WarriorDeck

class BasicPlayer implements Player {

    Deck deck = new WarriorDeck()
    List<Card> hand = new ArrayList()
    Strategy strategy = new AlwaysAttackStrategy()

    BasicPlayer(){
    }

    static constraints = {
    }
}
