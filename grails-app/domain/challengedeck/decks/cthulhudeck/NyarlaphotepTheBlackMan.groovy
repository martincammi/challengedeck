package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.actions.ActionHorrorAttackEachPlayerLose1Life
import challengedeck.actions.ActionHorrorCreaturesLessOneOne
import challengedeck.actions.ActionHorrorCreaturesPlusOneOne
import challengedeck.actions.ActionRevealAndCast
import challengedeck.actions.ActionRevealAndCastLegendaryCreature
import challengedeck.cards.Creature

class NyarlaphotepTheBlackMan extends Creature {

    NyarlaphotepTheBlackMan(){
        this.name = "Nyarlaphotep, The Black Man"
        this.cardTypes = [CardType.LEGENDARY, CardType.CREATURE]
        this.cardSubTypes = [CardSubType.HUMAN, CardSubType.SHAPESHIFTER]
        this.abilities = [new Ability(Trigger.STATIC_CREATURE_ENTERS_THE_BATTLEFIELD, ActionHorrorCreaturesPlusOneOne.class, this),
                          new Ability(Trigger.STATIC_CREATURE_LEAVES_THE_BATTLEFIELD, ActionHorrorCreaturesLessOneOne.class, this),
                          new Ability(Trigger.DECLARE_ATTACKERS_EACH, ActionHorrorAttackEachPlayerLose1Life.class, this),
                          new Ability(Trigger.LEAVES_THE_BATTLEFIELD, ActionRevealAndCastLegendaryCreature.class, this)
                        ]

        this.power = 1
        this.toughness = 1
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
