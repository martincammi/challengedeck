package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame
import challengedeck.cards.Card
import challengedeck.cards.Creature

class ActionReturnFirstCardGraveyardToBattlefield extends Action{

    void resolve(CthulhuGame game){
        if(!game.graveyard.isEmpty()){
            Card firstGraveyardCard = game.graveyard.first()
            game.graveyard.removeAt(0)
            println ability.card.name + ": Retrieving " + firstGraveyardCard.name + " from graveyard"
            if(firstGraveyardCard instanceof Creature){
                game.putOnBattlefield(firstGraveyardCard)
            }else{
                game.exile.add(firstGraveyardCard)
            }
            game.resolveStack()
        }else{
            println "No cards in graveyard to recover"
        }
    }

    static constraints = {
    }
}
