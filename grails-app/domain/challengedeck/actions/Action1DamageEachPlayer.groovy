package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.Trigger

class Action1DamageEachPlayer extends Action {

    void resolve(CthulhuGame game){
        game.triggersToStack(Trigger.CREATURE_DEALS_NON_COMBAT_DAMAGE)
        game.substractPlayerLife(1)
    }

    static constraints = {
    }
}
