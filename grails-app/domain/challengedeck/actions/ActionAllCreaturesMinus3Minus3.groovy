package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame

class ActionAllCreaturesMinus3Minus3 extends Action {

    void resolve(CthulhuGame game){
        game.getAllCreatures().each { c ->
            c.increasePower(-3)
            c.increaseToughness(-3)
        }
    }

    static constraints = {
    }
}
