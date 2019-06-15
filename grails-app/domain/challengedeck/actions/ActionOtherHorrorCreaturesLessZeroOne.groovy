package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Creature

class ActionOtherHorrorCreaturesLessZeroOne extends Action {

    void resolve(CthulhuGame game){
    }

    void resolve(CthulhuGame game, Object...values){
        if(values.first() instanceof Creature){
            Creature creature = (Creature) values.first()
            if(creature.cardSubTypes.contains(CardSubType.HORROR)){
                creature.toughness--
            }
        }
    }

    static constraints = {
    }
}
