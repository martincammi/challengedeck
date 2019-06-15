package challengedeck.abilities

import challengedeck.Trigger
import challengedeck.actions.ActionDeny
import challengedeck.cards.Card

class Hexproof extends Ability {

    Hexproof(Card card){
        this.trigger = Trigger.CAN_BE_TARGET
        this.action = ActionDeny.class
        this.card = card
    }
    static constraints = {
    }
}
