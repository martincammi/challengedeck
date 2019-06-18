package challengedeck.abilities

import challengedeck.Trigger
import challengedeck.actions.ActionBlockWithMoreThanOne
import challengedeck.actions.ActionDestroyCreature
import challengedeck.cards.Card

class Menace extends Ability {

    Menace(Card card){
        this.trigger = Trigger.CAN_BE_BLOCKED
        this.action = new ActionBlockWithMoreThanOne()
        this.card = card
    }
    static constraints = {
    }
}
