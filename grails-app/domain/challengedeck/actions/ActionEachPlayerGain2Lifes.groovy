package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame

class ActionEachPlayerGain2Lifes extends Action {

    void resolve(CthulhuGame game){
        game.addPlayerLife(2)
    }

    static constraints = {
    }
}
