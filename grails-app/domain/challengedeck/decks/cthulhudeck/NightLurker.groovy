package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.actions.ActionRevealAndCast
import challengedeck.cards.Creature

class NightLurker extends Creature {

    public NightLurker(){
        this.name = "Night Lurker"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.MINION, CardSubType.HORROR]
        this.abilities = [new Ability(Trigger.ENTER_THE_BATTLEFIELD, ActionRevealAndCast.class, this)]
        this.power = 2
        this.toughness = 2
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
