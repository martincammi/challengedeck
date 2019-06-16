package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Creature

class ActionHorrorCreaturesLessOneOne extends Action {

    void resolve(CthulhuGame game){
    }

    void resolve(CthulhuGame game, Object...values){
        if(values.first() instanceof Creature){
            Creature creature = (Creature) values.first()
            if(creature.cardSubTypes.contains(CardSubType.HORROR)){
                creature.increasePower(-1)
                creature.increaseToughness(-1)
            }
        }
    }

    static constraints = {
    }
}
