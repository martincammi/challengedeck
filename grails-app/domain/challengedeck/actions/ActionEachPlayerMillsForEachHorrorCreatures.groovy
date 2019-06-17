package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Card
import challengedeck.cards.Creature

class ActionEachPlayerMillsForEachHorrorCreatures extends Action {

    void resolve(CthulhuGame game){
        Integer amountOfHorrorCreatures = game.battlefield.count { it instanceof Creature && it.cardSubTypes.contains(CardSubType.HORROR)}
        Card card
        amountOfHorrorCreatures.times {
            card = game.player.deck.draw()
            game.playerGraveyard.add(card)
        }
    }

    static constraints = {
    }
}
