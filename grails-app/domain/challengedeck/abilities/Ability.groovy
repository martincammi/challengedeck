package challengedeck.abilities

import challengedeck.Trigger
import challengedeck.cards.Card

class Ability {

    Trigger trigger
    Class action
    Expire expire
    Card card //The card to which the ability belongs

    Ability(){

    }

    Ability(Trigger trigger, Class action, Card card){
        this.trigger = trigger
        this.action = action
        this.card = card
    }

    Ability(Trigger trigger, Class action, Expire expire, Card card){
        this.trigger = trigger
        this.action = action
        this.expire = expire
        this.card = card
    }

    static constraints = {
    }
}
