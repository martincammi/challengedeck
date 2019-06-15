package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame

class ActionRevealAndCast extends Action {

    void resolve(CthulhuGame game){
        game.cthulhuCast(1)
    }

    static constraints = {
    }
}
