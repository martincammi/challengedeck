package challengedeck.decks.cthulhudeck

import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.actions.ActionEachPlayerReturnsCreatureFromGraveyardToBattlefield
import challengedeck.actions.ActionRevealAndCast
import challengedeck.cards.Artifact

class CthulhuIdol extends Artifact {

    CthulhuIdol(){
        this.name = "Cthulhu Idol"
        this.cardTypes = [CardType.LEGENDARY, CardType.ARTIFACT]
        this.cardSubTypes = []
        this.abilities = [new Ability(Trigger.BEGINNING_MAIN_PHASE_1, ActionRevealAndCast.class, this),
                          new Ability(Trigger.LEAVES_THE_BATTLEFIELD, ActionEachPlayerReturnsCreatureFromGraveyardToBattlefield.class, this)]
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }

    @Override
    void resolve(CthulhuGame game) {
        game.putOnBattlefield(this)
        game.resolveStack()
    }
}
