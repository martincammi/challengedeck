package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame
import challengedeck.abilities.Haste
import challengedeck.cards.Card
import challengedeck.cards.Counter
import challengedeck.decks.cthulhudeck.CthulhuAncientLord

class ActionReturnToBattlefieldIfZeroCounters extends Action {

    void resolve(CthulhuGame game){
        println "Cthulhu ActionReturnToBattlefieldIfZeroCounters"
        Card cthulhu = game.exile.find { it instanceof CthulhuAncientLord }
        if (cthulhu){
            Integer cthulhuCounters = cthulhu.counters.get(Counter.TIME)
            if(cthulhuCounters == 0){
                game.exile.remove(cthulhu)
                cthulhu.abilities.add(new Haste(cthulhu))
                game.putOnBattlefield(cthulhu)
            }
        }
    }

    static constraints = {
    }
}
