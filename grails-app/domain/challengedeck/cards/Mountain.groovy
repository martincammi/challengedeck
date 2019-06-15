package challengedeck.cards

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.CthulhuGame

class Mountain extends Land {

    Mountain(){
        this.name = "Mountain"
        this.cardTypes = [CardType.LAND]
        this.cardSubTypes = [CardSubType.BASIC]
        this.abilities = []
        this.owner = "Basic Deck"
    }

    @Override
    void resolve(CthulhuGame game) {
        game.putOnBattlefield(this)
        game.resolveStack()
    }

    static constraints = {
    }

}
