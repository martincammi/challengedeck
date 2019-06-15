package challengedeck.decks.cthulhudeck

import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.abilities.Expire
import challengedeck.actions.ActionAllCreaturesMinus3Minus3
import challengedeck.cards.Card

class AwakingTremors extends Card {

    AwakingTremors(){
        this.name = "Awaking Tremors"
        this.cardTypes = [CardType.SORCERY]
        this.cardSubTypes = []
        this.abilities = [new Ability(Trigger.BEGINNING_OF_COMBAT, ActionAllCreaturesMinus3Minus3.class, Expire.END_OF_TURN, this)]
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    void resolve(CthulhuGame game){
        game.stepsAbilities.add(abilities.first())
    }


    static constraints = {
    }
}
