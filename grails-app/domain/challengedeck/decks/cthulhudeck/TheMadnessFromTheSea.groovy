package challengedeck.decks.cthulhudeck


import challengedeck.abilities.Expire
import challengedeck.cards.Card
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.actions.ActionHorror1DamageEachPlayer

class TheMadnessFromTheSea extends Card {

    TheMadnessFromTheSea(){
        this.name = "The Madness From The Sea"
        this.cardTypes = [CardType.SORCERY]
        this.cardSubTypes = []
        this.abilities = [new Ability(Trigger.BEGINNING_OF_COMBAT, ActionHorror1DamageEachPlayer.class, Expire.END_OF_TURN, this)]
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    void resolve(CthulhuGame game){
        game.stepsAbilities.add(abilities.first())
    }

    static constraints = {
    }
}
