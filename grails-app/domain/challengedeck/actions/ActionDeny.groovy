package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame

class ActionDeny extends Action {

    void resolve(CthulhuGame game){
        new Exception("ActionDeny not implemented")
    }

    static constraints = {
    }
}
