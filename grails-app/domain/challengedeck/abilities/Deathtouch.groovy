package challengedeck.abilities

import challengedeck.Trigger
import challengedeck.actions.ActionDestroyCreature
import challengedeck.cards.Card

class Deathtouch extends Ability {

    Deathtouch(Card card){
        this.trigger = Trigger.DAMAGE_CREATURE
        this.action = new ActionDestroyCreature()
        this.card = card
    }
    static constraints = {
    }
}
