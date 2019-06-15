package challengedeck.decks.basicdeck

import challengedeck.Deck
import challengedeck.cards.Mountain

class WarriorDeck extends Deck {

    WarriorDeck(){
        this.name = "Basic Deck"
        addMany(24, Mountain.class)
        addMany(36, RedWarrior.class)
        shuffle()
    }

    static constraints = {
    }
}
