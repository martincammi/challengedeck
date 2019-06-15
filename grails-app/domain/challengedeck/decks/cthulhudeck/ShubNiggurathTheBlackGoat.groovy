package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.abilities.Hexproof
import challengedeck.abilities.Trample
import challengedeck.actions.ActionCreateCultistToken
import challengedeck.actions.ActionCreateDarkYoungToken
import challengedeck.actions.ActionEachPlayerDrawsCard
import challengedeck.actions.ActionEachPlayerDrawsThreeCards
import challengedeck.actions.ActionLessPowerShubNiggurathNumberHorrors
import challengedeck.actions.ActionPlusPowerShubNiggurathNumberHorrors
import challengedeck.cards.Creature

class ShubNiggurathTheBlackGoat extends Creature {

    ShubNiggurathTheBlackGoat(){
        this.name = "Shub Niggurath, The Black Goat"
        this.cardTypes = [CardType.LEGENDARY, CardType.CREATURE]
        this.cardSubTypes = [CardSubType.HORROR, CardSubType.ELDER, CardSubType.GOD]
        this.abilities = [new Trample(this),
                          new Hexproof(this),
                          new Ability(Trigger.STATIC_CREATURE_ENTERS_THE_BATTLEFIELD, ActionPlusPowerShubNiggurathNumberHorrors.class, this),
                          new Ability(Trigger.STATIC_CREATURE_LEAVES_THE_BATTLEFIELD, ActionLessPowerShubNiggurathNumberHorrors.class, this),
                          new Ability(Trigger.BEGINNING_UPKEEP, ActionCreateDarkYoungToken.class, this),
                          new Ability(Trigger.LEAVES_THE_BATTLEFIELD, ActionEachPlayerDrawsThreeCards.class, this)]

        this.power = 0
        this.toughness = 0
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
