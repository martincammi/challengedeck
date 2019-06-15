package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame

class ActionEachPlayerDrawsCard extends Action {

    void resolve(CthulhuGame game){
        game.drawCard(game.player)
    }

    static constraints = {
    }
}
