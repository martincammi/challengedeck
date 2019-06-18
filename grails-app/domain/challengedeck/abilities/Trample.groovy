package challengedeck.abilities

import challengedeck.Trigger
import challengedeck.actions.ActionDeny
import challengedeck.cards.Card

class Trample extends Ability {

    Trample(Card card){
        this.trigger = Trigger.ASSIGN_COMBAT_DAMAGE
        this.action = new ActionDeny()
        this.card = card
    }
    static constraints = {
    }
}
