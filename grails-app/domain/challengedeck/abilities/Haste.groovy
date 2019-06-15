package challengedeck.abilities

import challengedeck.Trigger
import challengedeck.actions.ActionCanAttack
import challengedeck.actions.ActionDestroyCreature
import challengedeck.cards.Card

class Haste extends Ability {

    Haste(Card card){
        this.trigger = Trigger.DECLARE_ATTACKERS
        this.action = ActionCanAttack.class
        this.card = card
    }
    static constraints = {
    }
}
