package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.decks.cthulhudeck.MysticCaller
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class MysticCallerSpec extends SingleGame {

    void testMysticCallerOnly() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, MysticCaller.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(20,game.playerLife)
    }

    void testMysticCallerTwoCreature() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(1, MysticCaller.class)
        newDeck.addMany(1, WanderingReckoner.class) //Doesn't have Haste first time. No damage

        newDeck.addMany(2, WanderingReckoner.class) //Doesn't have Haste first time. 2 damage from previous WanderingRackoner

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(18, game.playerLife)
    }

    void testTwoMysticCaller() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, WanderingReckoner.class)
        newDeck.addMany(2, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(16,game.playerLife)
    }

    void testTwoMysticCallerNonHorrorCreatures() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, MysticCaller.class)
        newDeck.addMany(2, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(20,game.playerLife)
    }

    void testTwoMysticCallerRealGame() {

        CthulhuGame game = new CthulhuGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, MysticCaller.class)
        newDeck.addMany(2, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(20,game.playerLife)
    }
}
