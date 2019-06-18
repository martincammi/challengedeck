package challengedeck.decks.cthulhudeck

import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.abilities.Expire
import challengedeck.actions.ActionExileAllHorrorCreatures
import challengedeck.actions.ActionReturnAllHorrorCreaturesExiled
import challengedeck.cards.Sorcery

class PreparingTheComing extends Sorcery {


    PreparingTheComing(){
        this.name = "Preparing The Coming"
        this.cardTypes = [CardType.SORCERY]
        this.cardSubTypes = []
        this.abilities = [new Ability(Trigger.RESOLVE, ActionExileAllHorrorCreatures.class, this),
                          new Ability(Trigger.BEGINNING_OF_COMBAT_NEXT_TURN, ActionReturnAllHorrorCreaturesExiled.class, Expire.END_OF_NEXT_TURN, this)

        ]
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    @Override
    void resolve(CthulhuGame game) {
        game.stepsAbilities.add(abilities.last())
        game.resolveStack()
    }

    static constraints = {
    }
}
