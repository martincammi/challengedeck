package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame
import challengedeck.cards.Creature

class ActionDestroyAllCreatures extends Action{

    void resolve(CthulhuGame game){
        game.battlefield.clone().each { c -> //Clone is because we are iterating the battlefield and trying to modify it at the same time
            if(c instanceof Creature){
                game.leaveTheBattlefield(c)
            }
        }
    }

    static constraints = {
    }
}
