package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.abilities.Haste
import challengedeck.cards.Creature

class ActionHorrorAttack extends Action {

    void resolve(CthulhuGame game){
        game.battlefield.findAll{ c -> c.cardSubTypes.contains(CardSubType.HORROR) && c instanceof Creature }.each { Creature c -> c.shouldAttack = true}
    }

    static constraints = {
    }
}
