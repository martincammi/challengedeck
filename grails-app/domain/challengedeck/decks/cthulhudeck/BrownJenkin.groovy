package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.actions.ActionEachPlayerDiscardCard
import challengedeck.actions.ActionEachPlayerDrawsCard
import challengedeck.actions.ActionPlayerDiscardCard
import challengedeck.cards.Creature

class BrownJenkin extends Creature {

    BrownJenkin(){
        this.name = "Brown Jenkin"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.HUMAN, CardSubType.RAT, CardSubType.HORROR]
        this.abilities = [new Ability(Trigger.ENTER_THE_BATTLEFIELD, ActionEachPlayerDiscardCard.class, this),
                          new Ability(Trigger.CREATURE_DEALS_NON_COMBAT_DAMAGE, ActionPlayerDiscardCard.class, this), //TODO pending define which player
                          new Ability(Trigger.LEAVES_THE_BATTLEFIELD, ActionEachPlayerDrawsCard.class, this)]
        this.power = 1
        this.toughness = 3
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
