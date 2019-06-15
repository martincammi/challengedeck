package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.cards.Creature
import challengedeck.decks.cthulhudeck.BrownJenkin
import challengedeck.decks.cthulhudeck.TheArkhamWitch
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class TheArkhamWitchSpec extends SingleGame {

    void testTheArkhamWitchOnly() {

        CthulhuGame game =  getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, TheArkhamWitch.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(18,game.playerLife)
    }

    void testTheArkhamWitchCreature() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, TheArkhamWitch.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(19,game.playerLife)
    }

    void testTheArkhamWitchDies() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, TheArkhamWitch.class)


        game.cthulhuDeck = newDeck
        game.start(1)
        Creature theArkhamWitch = game.battlefield.first()
        game.sacrifice(theArkhamWitch)
        game.resolveStack()

        assertEquals(20,game.playerLife)
    }

    void testTheArkhamWitchDamageDiscard() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, TheArkhamWitch.class)
        newDeck.addMany(1, WanderingReckoner.class)


        game.cthulhuDeck = newDeck
        game.start(1)
        game.discardCard(game.player)
        game.resolveStack()

        assertEquals(6,game.player.hand.size())
        assertEquals(18,game.playerLife)
    }

    void testTheArkhamWitchBrownJenkin() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, TheArkhamWitch.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(1, BrownJenkin.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(0,game.player.hand.size())
        assertEquals(12,game.playerLife)
    }
}
