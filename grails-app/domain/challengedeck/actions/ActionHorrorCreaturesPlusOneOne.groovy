package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Creature
import challengedeck.decks.cthulhudeck.ShubNiggurathTheBlackGoat

class ActionHorrorCreaturesPlusOneOne extends Action {

    void resolve(CthulhuGame game){

        game.battlefield.each { c ->
            if(c instanceof Creature){
                resolve(game, c)
            }

        }
    }

    void resolve(CthulhuGame game, Object...values){
        Creature creature = (Creature) values.first()
        if(creature.cardSubTypes.contains(CardSubType.HORROR)){
            creature.increasePower(1)
            creature.increaseToughness(1)
        }
    }

    static constraints = {
    }
}
