package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.Trigger
import challengedeck.abilities.Ability
import challengedeck.abilities.Deathtouch
import challengedeck.abilities.Hexproof
import challengedeck.actions.ActionEachPlayerMillsForEachHorrorCreatures
import challengedeck.actions.ActionEachPlayerShuffleUp5CardsGraveyardLibrary
import challengedeck.cards.Creature

class NyarlathotepCrawlingChaos extends Creature {

    NyarlathotepCrawlingChaos(){
        this.name = "Nyarlathotep, Crawling Chaos"
        this.cardTypes = [CardType.LEGENDARY, CardType.CREATURE]
        this.cardSubTypes = [CardSubType.HORROR, CardSubType.ELDER, CardSubType.GOD]
        this.abilities = [new Deathtouch(this),
                          new Hexproof(this),
                          new Ability(Trigger.ENTER_THE_BATTLEFIELD, ActionEachPlayerMillsForEachHorrorCreatures.class, this),
                          new Ability(Trigger.BEGINNING_UPKEEP, ActionEachPlayerMillsForEachHorrorCreatures.class, this),
                          new Ability(Trigger.LEAVES_THE_BATTLEFIELD, ActionEachPlayerShuffleUp5CardsGraveyardLibrary.class, this)
        ]

        this.power = 5
        this.toughness = 5
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
