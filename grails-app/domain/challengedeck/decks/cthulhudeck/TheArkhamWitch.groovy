package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.actions.Action1DamageEachPlayer
import challengedeck.actions.ActionEachPlayerGain2Lifes
import challengedeck.cards.Creature

class TheArkhamWitch extends Creature {

    TheArkhamWitch(){
        this.name = "The Arkham Witch"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.HUMAN, CardSubType.SHAMAN]
        this.abilities = [new Ability(Trigger.ENTER_THE_BATTLEFIELD, Action1DamageEachPlayer.class, this),
                          new Ability(Trigger.PLAYER_DISCARD_CARD, Action1DamageEachPlayer.class, this), //TODO if many players need to adapt this.
                          new Ability(Trigger.LEAVES_THE_BATTLEFIELD, ActionEachPlayerGain2Lifes.class, this)]
        this.power = 2
        this.toughness = 2
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
