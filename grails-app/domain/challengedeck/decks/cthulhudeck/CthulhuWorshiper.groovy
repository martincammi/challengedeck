package challengedeck.decks.cthulhudeck

import challengedeck.*
import challengedeck.abilities.Ability
import challengedeck.actions.ActionCreateCultistToken
import challengedeck.actions.ActionSacrificeCultistsSearchCthulhu
import challengedeck.cards.Creature

class CthulhuWorshiper extends Creature {

    CthulhuWorshiper(){
        this.name = "Cthulhu Worshiper"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.CULTIST]
        this.abilities = [new Ability(Trigger.BEGINNING_UPKEEP, ActionSacrificeCultistsSearchCthulhu.class, this),
                          new Ability(Trigger.BEGINNING_END_STEP, ActionCreateCultistToken.class, this)]
        this.power = 1
        this.toughness = 1
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
