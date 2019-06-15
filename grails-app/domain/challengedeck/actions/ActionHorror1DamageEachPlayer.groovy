package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.Trigger

class ActionHorror1DamageEachPlayer extends Action {

    void resolve(CthulhuGame game){
        Integer horrorCreatures = game.battlefield.count { c -> c.cardSubTypes.contains(CardSubType.HORROR)}
        game.substractPlayerLife(horrorCreatures)
        game.triggersToStack(Trigger.CREATURE_DEALS_NON_COMBAT_DAMAGE)
    }

    static constraints = {
    }
}
