package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Card
import challengedeck.cards.Creature

class ActionEachPlayerShuffleUp5CardsGraveyardLibrary extends Action {

    void resolve(CthulhuGame game){

        List cardsToLibrary = new ArrayList()
        List graveyard = game.playerGraveyard.clone()
        Collections.shuffle(graveyard)
        Card card

        5.times {
            if(!graveyard.isEmpty()){
                card = graveyard.pop()
                cardsToLibrary.add(card)
                game.playerGraveyard.remove(card)
            }
        }
        cardsToLibrary.each {
            game.player.deck.add(it)
            game.player.deck.shuffle()
        }
    }

    static constraints = {
    }
}
