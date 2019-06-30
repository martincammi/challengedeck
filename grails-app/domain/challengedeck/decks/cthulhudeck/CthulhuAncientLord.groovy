package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.abilities.Deathtouch
import challengedeck.abilities.Flying
import challengedeck.abilities.Trample
import challengedeck.actions.ActionDenyIfAnyHorrorCreature
import challengedeck.actions.ActionRemoveCounterFromCthulhu
import challengedeck.actions.ActionReturnToBattlefieldIfZeroCounters
import challengedeck.actions.ActionSuspendItWithXMinusHorrorTimeCounters
import challengedeck.cards.Creature

class CthulhuAncientLord extends Creature {

    CthulhuAncientLord() {
        this.name = "Cthulhu, Ancient Lord"
        this.cardTypes = [CardType.CREATURE]
        this.cardSubTypes = [CardSubType.OCTOPUS, CardSubType.HORROR]
        this.abilities = [new Flying(this),
                          new Deathtouch(this),
                          new Trample(this),
                          //Cant be countered,
                          new Ability(Trigger.REVEAL, ActionSuspendItWithXMinusHorrorTimeCounters.class, this),
                          new Ability(Trigger.EXILE_STATIC_CREATURE_ENTERS_THE_BATTLEFIELD, ActionRemoveCounterFromCthulhu.class, this),
                          new Ability(Trigger.WOULD_LEAVE_THE_BATTLEFIELD, ActionDenyIfAnyHorrorCreature.class, this),
                          new Ability(Trigger.CHECK_SUSPEND, ActionReturnToBattlefieldIfZeroCounters.class, this)
        ]
        this.power = 13
        this.toughness = 13
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }

    @Override
    void resolve(CthulhuGame game) {
        println "Behold Mortal!"
        super.resolve(game)
    }

}
