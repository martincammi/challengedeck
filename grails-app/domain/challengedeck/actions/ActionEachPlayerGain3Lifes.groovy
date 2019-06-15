package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame

class ActionEachPlayerGain3Lifes extends Action {

    void resolve(CthulhuGame game){
        game.addPlayerLife(3)
    }

    static constraints = {
    }
}
