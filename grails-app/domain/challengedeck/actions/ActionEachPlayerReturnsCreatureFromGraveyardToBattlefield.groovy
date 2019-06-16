package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame
import challengedeck.cards.Card

class ActionEachPlayerReturnsCreatureFromGraveyardToBattlefield extends Action {

    void resolve(CthulhuGame game){
        List<Card> playerGraveyard = game.playerGraveyard.clone()
        Collections.shuffle(playerGraveyard)
        if(!playerGraveyard.isEmpty()){
            Card card = playerGraveyard.first()
            game.playerGraveyard.remove(card)
            game.putOnBattlefield(card)
            game.resolveStack()
        }
    }

    static constraints = {
    }
}
