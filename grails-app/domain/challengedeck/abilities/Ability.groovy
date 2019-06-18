package challengedeck.abilities

import challengedeck.Action
import challengedeck.Trigger
import challengedeck.cards.Card

class Ability {

    Trigger trigger
    Action action
    Expire expire
    Card card //The card to which the ability ability

    Ability(){

    }

    Ability(Trigger trigger, Class action, Card card){
        this.trigger = trigger
        this.action = action.newInstance()
        this.action.ability = this
        this.card = card
    }

    Ability(Trigger trigger, Class action, Expire expire, Card card){
        this.trigger = trigger
        this.action = action.newInstance()
        this.action.ability = this
        this.expire = expire
        this.card = card
    }

    static constraints = {
    }
}
