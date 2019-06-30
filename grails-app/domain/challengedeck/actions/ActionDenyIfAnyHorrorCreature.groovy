package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Creature
import challengedeck.exceptions.CantLeaveBattlefieldException

class ActionDenyIfAnyHorrorCreature extends Action {

    void resolve(CthulhuGame game){
    }

    void resolve(CthulhuGame game,  Object...values){

        if(ability.card.class.isInstance(values.first())) { //Check if the creature leaving the battlefield is Cthulhu
            Boolean anyHorror = game.battlefield.any { it instanceof Creature && it.cardSubTypes.contains(CardSubType.HORROR)}
            if(anyHorror){
                throw new CantLeaveBattlefieldException()
            }
        }
    }
    static constraints = {
    }
}
