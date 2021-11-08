package challengedeck.decks.basicdeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.cards.Creature

class RedWarrior extends Creature {

    RedWarrior(){
        this.name = "Red Warrior"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.HUMAN, CardSubType.WARRIOR]
        this.abilities = []
        this.power = 2
        this.toughness = 2
//        this.owner = "Basic Deck"
        this.cost = 2
    }

    static constraints = {
    }
}
