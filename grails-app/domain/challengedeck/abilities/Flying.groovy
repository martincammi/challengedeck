package challengedeck.abilities

import challengedeck.Trigger
import challengedeck.actions.ActionDeny
import challengedeck.actions.ActionDestroyCreature
import challengedeck.cards.Card

class Flying extends Ability {

    Flying(Card card){
        this.trigger = Trigger.CAN_BE_BLOCKED
        this.action = ActionDeny.class
        this.card = card
    }
    static constraints = {
    }
}
