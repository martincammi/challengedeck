package challengedeck.decks.cthulhudeck

import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.abilities.Expire
import challengedeck.actions.ActionHorrorGainHasteAndAttack
import challengedeck.cards.Card

class TheCallOfCthulhu extends Card {

    TheCallOfCthulhu(){
        this.name = "The Call Of Cthulhu"
        this.cardTypes = [CardType.SORCERY]
        this.cardSubTypes = []
        this.abilities = [new Ability(Trigger.BEGINNING_OF_COMBAT, ActionHorrorGainHasteAndAttack.class, Expire.END_OF_TURN, this)]
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    void resolve(CthulhuGame game){
        game.stepsAbilities.add(abilities.first())
    }

    static constraints = {
    }
}
