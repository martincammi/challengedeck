package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Creature
import challengedeck.decks.cthulhudeck.ShubNiggurathTheBlackGoat

class ActionLessPowerShubNiggurathNumberHorrors extends Action {

    void resolve(CthulhuGame game){
    }

    void resolve(CthulhuGame game, Object...values){
        if(values.first() instanceof Creature){
            Creature creature = (Creature) values.first()
            if(creature.cardSubTypes.contains(CardSubType.HORROR)){
                Creature shubNiggurath = game.battlefield.find { c -> c instanceof ShubNiggurathTheBlackGoat }
                shubNiggurath.increasePower(-1)
                shubNiggurath.increaseToughness(-1)
            }
        }
    }

    static constraints = {
    }
}
