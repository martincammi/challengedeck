package challengedeck.actions

import challengedeck.Action
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.cards.Card
import challengedeck.cards.Counter
import challengedeck.cards.Creature

class ActionPutFishCounter extends Action {

    void resolve(CthulhuGame game){
        List<Creature> creaturesWithoutFishCounters = game.battlefield.findAll { c -> c instanceof Creature && !c.hasCounter(Counter.FISH)}

        Creature creature = creaturesWithoutFishCounters.min { it.toughness }
        if(creature){
            creature.counters.put(Counter.FISH, 1)
            creature.show()

        }
    }

    static constraints = {
    }
}
