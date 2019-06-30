package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Counter
import challengedeck.cards.Creature
import challengedeck.decks.cthulhudeck.CthulhuAncientLord

class ActionSacrificeCultistsSearchCthulhu extends Action {

    void resolve(CthulhuGame game){
        List<Creature> cultists = game.getAllCreatures().findAll { c -> c.cardSubTypes.contains(CardSubType.CULTIST)}

        if(cultists.size() >= 5){
            println "Sacrificing all Cultists"
            game.sacrificeAll(cultists)

            Creature cthulu = game.exile.find{ c -> c instanceof CthulhuAncientLord}
            if(cthulu){
                println "Cthulu enters the battlefield!"
                game.removeAllCounters(cthulu, Counter.TIME)
            }else{
                cthulu = game.cthulhuDeck.cards.find{ c -> c instanceof CthulhuAncientLord}
                if(cthulu){
                    game.reveal(cthulu)
                    println "Cthulu sent to exile!"
                }else{
                    //Cthulu is already on the battlefield
                }
            }
        }
    }

    static constraints = {
    }
}
