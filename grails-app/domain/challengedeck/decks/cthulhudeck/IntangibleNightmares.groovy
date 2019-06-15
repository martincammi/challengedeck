package challengedeck.decks.cthulhudeck

import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.actions.ActionEachPlayerDiscardCard
import challengedeck.cards.Card
import challengedeck.cards.Sorcery

class IntangibleNightmares extends Sorcery {

    IntangibleNightmares(){
        this.name = "Intangible Nightmares"
        this.cardTypes = [CardType.SORCERY]
        this.cardSubTypes = []
        this.abilities = [new Ability(Trigger.RESOLVE, ActionEachPlayerDiscardCard.class, this)]
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    @Override
    void resolve(CthulhuGame game) {
        game.resolveStack()
    }

    static constraints = {
    }
}
