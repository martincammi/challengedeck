package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.abilities.Menace
import challengedeck.cards.Creature
import challengedeck.actions.ActionEachPlayerGain3Lifes

class LurkingFear extends Creature {

    LurkingFear(){
        this.name = "Lurking Fear"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.HORROR]
        this.abilities = [new Menace(this),
                          new Ability(Trigger.LEAVES_THE_BATTLEFIELD, ActionEachPlayerGain3Lifes.class, this)]
        this.power = 3
        this.toughness = 1
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
