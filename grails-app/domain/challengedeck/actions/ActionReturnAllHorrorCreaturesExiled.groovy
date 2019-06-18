package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Creature

class ActionReturnAllHorrorCreaturesExiled extends Action {

    void resolve(CthulhuGame game){

        List cardsExiledWithSameCard = game.exile.findAll{ c -> c.exiledBy.equals(this.ability.card) }

        cardsExiledWithSameCard.each{ c ->
            if(c.cardSubTypes.contains(CardSubType.HORROR) && c instanceof Creature){
                game.putOnBattlefield(c)
                game.exile.remove(c)
                game.resolveStack()
            }
        }
    }

    static constraints = {
    }
}
