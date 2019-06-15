package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.abilities.Flying
import challengedeck.abilities.Hexproof
import challengedeck.actions.ActionEachPlayerLoseLifeCardsInHand
import challengedeck.actions.ActionOtherHorrorCreaturesLessZeroOne
import challengedeck.actions.ActionOtherHorrorCreaturesPlusZeroOne
import challengedeck.cards.Creature

class YogSothothTheOmniscient extends Creature {

    YogSothothTheOmniscient(){
        this.name = "Yog Sothoth, The Omniscient"
        this.cardTypes = [CardType.LEGENDARY, CardType.CREATURE]
        this.cardSubTypes = [CardSubType.HORROR, CardSubType.ELDER, CardSubType.GOD]
        this.abilities = [new Flying(this),
                          new Hexproof(this),
                          new Ability(Trigger.STATIC_CREATURE_ENTERS_THE_BATTLEFIELD, ActionOtherHorrorCreaturesPlusZeroOne.class, this),
                          new Ability(Trigger.STATIC_CREATURE_LEAVES_THE_BATTLEFIELD, ActionOtherHorrorCreaturesLessZeroOne.class, this),
                          //Discard a Card ability: pending
                          new Ability(Trigger.BEGINNING_UPKEEP, ActionEachPlayerLoseLifeCardsInHand.class, this)]
        this.power = 6
        this.toughness = 6
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
