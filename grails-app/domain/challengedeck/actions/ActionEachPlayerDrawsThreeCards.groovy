package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame

class ActionEachPlayerDrawsThreeCards extends Action {

    void resolve(CthulhuGame game){
        game.drawCard(game.player)
        game.drawCard(game.player)
        game.drawCard(game.player)
    }

    static constraints = {
    }
}
