package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame

class ActionEachPlayerDiscardCard extends Action {

    void resolve(CthulhuGame game){
        game.discardCard(game.player)
    }

    static constraints = {
    }
}
