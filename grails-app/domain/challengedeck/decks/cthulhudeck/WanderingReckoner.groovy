package challengedeck.decks.cthulhudeck

import challengedeck.*
import challengedeck.abilities.Ability
import challengedeck.actions.ActionEachPlayerGain2Lifes
import challengedeck.cards.Creature

class WanderingReckoner extends Creature {

    WanderingReckoner(){
        this.name = "Wandering Reckoner"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.HUMAN, CardSubType.HORROR]
        this.abilities = [new Ability(Trigger.LEAVES_THE_BATTLEFIELD, ActionEachPlayerGain2Lifes.class, this)]
        this.power = 2
        this.toughness = 2
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
