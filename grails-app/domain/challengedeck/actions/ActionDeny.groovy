package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame

class ActionDeny extends Action {

    void resolve(CthulhuGame game){
        throw new Exception("ActionDeny not implemented")
    }

    static constraints = {
    }
}
