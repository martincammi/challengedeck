package challengedeck.decks.cthulhudeck

import challengedeck.Deck
import challengedeck.decks.cthulhudeck.InnsmouthOffspring
import challengedeck.decks.cthulhudeck.NightLurker
import challengedeck.decks.cthulhudeck.TheCallOfCthulhu
import challengedeck.decks.cthulhudeck.TheMadnessFromTheSea
import challengedeck.decks.cthulhudeck.WanderingReckoner

class CthulhuDeck extends Deck {

    CthulhuDeck(){

        this.name = "Nightmare dreams"
        addMany(6, NightLurker.class)
        addMany(5, WanderingReckoner.class)
        addMany(2, TheMadnessFromTheSea.class)
        addMany(2, InnsmouthOffspring.class)
        addMany(7, TheCallOfCthulhu.class)
        addMany(1, TheArkhamWitch.class)
        addMany(1, BrownJenkin.class)
        addMany(2, LurkingFear.class)
        addMany(2, AwakingTremors.class)
        addMany(16, MysticCaller.class)
        addMany(2, VenomousScourger.class)
        addMany(3, CthulhuWorshiper.class)
        addMany(1, YogSothothTheOmniscient.class)
        addMany(1, ShubNiggurathTheBlackGoat.class)
        addMany(2, IntangibleNightmares.class)

        //Pending
        addMany(1, CthulhuAncientLord.class)
        addMany(1, Necronomicon.class)
        addMany(1, CthulhuIdol.class)
        addMany(1, NyarlaphotepTheBlackMan.class)
        //addMany(1, NyarlathotepCrawlingChaos.class)
        //addMany(2, PreparingTheComing.class)


        shuffle()

    }

    static String CTHULHU_NAME = "CTHULHU"

    static constraints = {
    }
}
