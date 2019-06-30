package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.cards.Creature
import challengedeck.decks.cthulhudeck.YogSothothTheOmniscient

class ActionOtherHorrorCreaturesPlusZeroOne extends Action {

    void resolve(CthulhuGame game){
        game.battlefield.each { c ->

            if(c instanceof Creature && c.cardSubTypes.contains(CardSubType.HORROR) && !(c instanceof YogSothothTheOmniscient)){
                resolve(game, c)
            }

        }
    }

    void resolve(CthulhuGame game, Object...values){
        Creature creature = (Creature) values.first()
        if(creature.cardSubTypes.contains(CardSubType.HORROR)){
            creature.toughness++
        }
    }

    static constraints = {
    }
}
