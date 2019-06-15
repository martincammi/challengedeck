package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.cards.Creature

class CthulhuAncientLord extends Creature {

    CthulhuAncientLord(){
        this.name = "Cthulhu, Ancient Lord"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.OCTOPUS, CardSubType.HORROR]
        this.abilities = []
        this.power = 13
        this.toughness = 13
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }

    @Override
    void resolve(CthulhuGame game) {
        println "Behold Mortal!"
    }
}
