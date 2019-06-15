package challengedeck.decks.cthulhudeck

import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.actions.ActionInnsmouthSacrificeOrGainControl
import challengedeck.actions.ActionPutFishCounter
import challengedeck.cards.Enchantment

class InnsmouthOffspring extends Enchantment {

    InnsmouthOffspring(){
        this.name = "Innsmouth Offspring"
        this.cardTypes = [CardType.ENCHANTMENT]
        this.cardSubTypes = []
        this.abilities = [new Ability(Trigger.BEGINNING_END_STEP, ActionPutFishCounter.class, this),
                          new Ability(Trigger.BEGINNING_UPKEEP, ActionInnsmouthSacrificeOrGainControl.class, this)]
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    @Override
    void resolve(CthulhuGame game) {
        game.putOnBattlefield(this)
        game.resolveStack()
//        game.stepsAbilities.addAll(abilities)
    }

    static constraints = {
    }
}
