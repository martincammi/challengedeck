package challengedeck.decks.cthulhudeck

import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.actions.ActionDestroyAllCreatures
import challengedeck.actions.ActionReturnFirstCardGraveyardToBattlefield
import challengedeck.cards.Artifact

class Necronomicon extends Artifact {

    Necronomicon(){
        this.name = "Necronomicon"
        this.cardTypes = [CardType.LEGENDARY, CardType.ARTIFACT]
        this.cardSubTypes = []
        this.abilities = [new Ability(Trigger.BEGINNING_MAIN_PHASE_1, ActionReturnFirstCardGraveyardToBattlefield.class, this),
                          new Ability(Trigger.LEAVES_THE_BATTLEFIELD, ActionDestroyAllCreatures.class, this)]
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
