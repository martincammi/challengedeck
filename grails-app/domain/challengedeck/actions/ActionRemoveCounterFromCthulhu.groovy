package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Card
import challengedeck.cards.Counter
import challengedeck.cards.Creature
import challengedeck.decks.cthulhudeck.CthulhuAncientLord

class ActionRemoveCounterFromCthulhu extends Action {

    void resolve(CthulhuGame game){}

    void resolve(CthulhuGame game, Object...values){

        Creature creature = (Creature) values.first()
        if(creature.cardSubTypes.contains(CardSubType.HORROR)){
            Card cthulhu = game.exile.find { it instanceof CthulhuAncientLord }
            if(cthulhu){
                game.removeCounter(cthulhu,Counter.TIME)
            }
        }
    }

    static constraints = {
    }
}
