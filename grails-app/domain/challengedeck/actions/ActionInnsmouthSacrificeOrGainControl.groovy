package challengedeck.actions

import challengedeck.Action
import challengedeck.decks.cthulhudeck.CthulhuDeck
import challengedeck.CthulhuGame
import challengedeck.cards.Counter
import challengedeck.cards.Creature
import challengedeck.decks.cthulhudeck.InnsmouthOffspring

class ActionInnsmouthSacrificeOrGainControl extends Action {

    void resolve(CthulhuGame game) {


        //Search for creatures with a fish counter not controlled by Cthulhu
        List<Creature> creaturesWithFishCounter = game.battlefield.findAll { c -> c instanceof Creature && c.hasCounter(Counter.FISH) &&
                c.controller.equals(CthulhuDeck.CTHULHU_NAME) }
        Collections.shuffle(creaturesWithFishCounter)

        if(creaturesWithFishCounter) {
            //Select creature at random, and Cthulu gains control of it
            Creature creatureChosen = creaturesWithFishCounter.first()
            creatureChosen.controller = CthulhuDeck.CTHULHU_NAME

        }else{
            //Check if exist permanent with Fish Counter, if not sacrifice Innsmouth Offspring
            Boolean hasFish = game.battlefield.any { p -> p.counters.containsKey(Counter.FISH) }

            if(!hasFish){
                game.sacrifice(game.battlefield.find { p -> p.name.equals(new InnsmouthOffspring().name)})
            }
        }

    }
}
