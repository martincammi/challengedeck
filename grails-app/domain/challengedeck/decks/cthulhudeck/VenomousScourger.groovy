package challengedeck.decks.cthulhudeck

import challengedeck.*
import challengedeck.abilities.Ability
import challengedeck.abilities.Deathtouch
import challengedeck.actions.ActionAllCreaturesGainDeathtouch
import challengedeck.cards.Creature

class VenomousScourger extends Creature {

    public VenomousScourger(){
        this.name = "Venomous Scourger"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.HORROR]
        this.abilities = [new Deathtouch(this),
                          new Ability(Trigger.LEAVES_THE_BATTLEFIELD, ActionAllCreaturesGainDeathtouch.class, this)]
        this.power = 3
        this.toughness = 2
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
