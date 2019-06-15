package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class IntangibleNightmaresSpec extends SingleGame {

    void testIntangibleNightmaresOnly() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, IntangibleNightmares.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(6,game.player.hand.size())
    }

    void testTwoIntangibleNightmares() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, IntangibleNightmares.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(5,game.player.hand.size())
    }

    void testEightIntangibleNightmares() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, IntangibleNightmares.class)
        newDeck.addMany(2, IntangibleNightmares.class)
        newDeck.addMany(2, IntangibleNightmares.class)
        newDeck.addMany(2, IntangibleNightmares.class)

        game.cthulhuDeck = newDeck
        game.start(4)

        assertEquals(0,game.player.hand.size())
    }

    void testIntangibleNightmaresArkhamWitch() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, IntangibleNightmares.class)
        newDeck.addMany(1, TheArkhamWitch.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(19,game.playerLife)
        assertEquals(6,game.player.hand.size())
    }
}
