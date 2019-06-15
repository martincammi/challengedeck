package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.actions.ActionHorrorAttack
import challengedeck.cards.Creature

class MysticCaller extends Creature {

    MysticCaller(){
        this.name = "Mystic Caller"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.CULTIST, CardSubType.HUMAN]
        this.abilities = [new Ability(Trigger.BEGINNING_OF_COMBAT, ActionHorrorAttack.class, this)]
        this.power = 2
        this.toughness = 2
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
