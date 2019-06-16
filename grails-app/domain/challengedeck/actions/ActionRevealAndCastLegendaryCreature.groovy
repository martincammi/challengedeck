package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.cards.Card
import challengedeck.cards.Creature

class ActionRevealAndCastLegendaryCreature extends Action {

    void resolve(CthulhuGame game){

        List cardsRevealed = new ArrayList();
        Card card = game.reveal()
        while(card != null){

            if(card instanceof Creature && card.cardTypes.contains(CardType.LEGENDARY)){
                game.putOnBattlefield(card)
                game.resolveStack()
                break
            }
            cardsRevealed.add(card)
            card = game.reveal()
        }

        cardsRevealed.each {
            game.cthulhuDeck.add(it)
        }
        Collections.shuffle(cardsRevealed)

        if(card == null){
            println "Reached end of deck, no Legendary Creature found."
        }
    }

    static constraints = {
    }
}
