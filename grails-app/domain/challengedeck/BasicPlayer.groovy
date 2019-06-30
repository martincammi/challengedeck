package challengedeck

import challengedeck.cards.Card
import challengedeck.cards.Player
import challengedeck.cards.Strategy
import challengedeck.decks.basicdeck.WarriorDeck
import challengedeck.strategies.AlwaysAttackPlayerStrategy

class BasicPlayer implements Player {

    Deck deck = new WarriorDeck()
    List<Card> hand = new ArrayList()
    Strategy strategy = new AlwaysAttackPlayerStrategy()

    BasicPlayer(){
    }

    static constraints = {
    }
}
