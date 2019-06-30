package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.cards.Counter

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class CthulhuAncientLordSpec extends SingleGame {

    void testCthulhuAncientLordOnlyFirst() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(1, CthulhuAncientLord.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(1,game.battlefield.size())
        assertEquals(1,game.exile.size())
        assertTrue(game.battlefield.any { c -> c instanceof WanderingReckoner })
        assertTrue(game.exile.any { c -> c instanceof CthulhuAncientLord })
        assertEquals(9, game.exile.find { c -> c instanceof CthulhuAncientLord }.getCounter(Counter.TIME))
        assertFalse(game.playerWin)
    }

    void testCthulhuAncientLordOnlySecond() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(1, WanderingReckoner.class)
        newDeck.addMany(1, CthulhuAncientLord.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(1,game.battlefield.size())
        assertEquals(1,game.exile.size())
        assertTrue(game.battlefield.any { c -> c instanceof WanderingReckoner })
        assertTrue(game.exile.any { c -> c instanceof CthulhuAncientLord })
        assertEquals(9, game.exile.find { c -> c instanceof CthulhuAncientLord }.getCounter(Counter.TIME))
        assertFalse(game.playerWin)
    }

    void testCthulhuAncientLordHorrors() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(2, WanderingReckoner.class)

        newDeck.addMany(1, CthulhuAncientLord.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(3,game.battlefield.size())
        assertEquals(1,game.exile.size())
        assertTrue(game.battlefield.any { c -> c instanceof WanderingReckoner || c instanceof MysticCaller })
        assertTrue(game.exile.any { c -> c instanceof CthulhuAncientLord })
        assertEquals(8, game.exile.find { c -> c instanceof CthulhuAncientLord }.getCounter(Counter.TIME))
        assertFalse(game.playerWin)
    }

    void testCthulhuAncientLordNonHorrors() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(2, MysticCaller.class)

        newDeck.addMany(1, CthulhuAncientLord.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(3,game.battlefield.size())
        assertEquals(1,game.exile.size())
        assertTrue(game.battlefield.any { c -> c instanceof MysticCaller })
        assertTrue(game.exile.any { c -> c instanceof CthulhuAncientLord })
        assertEquals(10, game.exile.find { c -> c instanceof CthulhuAncientLord }.getCounter(Counter.TIME))
        assertFalse(game.playerWin)
    }

    void testCthulhuAncientLordMixedHorrorsNonHorrors() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(1, WanderingReckoner.class)
        newDeck.addMany(1, MysticCaller.class)

        newDeck.addMany(1, CthulhuAncientLord.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(3,game.battlefield.size())
        assertEquals(1,game.exile.size())
        assertTrue(game.battlefield.any { c -> c instanceof WanderingReckoner || c instanceof MysticCaller })
        assertTrue(game.exile.any { c -> c instanceof CthulhuAncientLord })
        assertEquals(9, game.exile.find { c -> c instanceof CthulhuAncientLord }.getCounter(Counter.TIME))
        assertFalse(game.playerWin)
    }

    void testCthulhuAncientLordEnterTheBattlefield() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(2, WanderingReckoner.class)
        newDeck.addMany(2, WanderingReckoner.class)
        newDeck.addMany(2, WanderingReckoner.class)
        newDeck.addMany(2, WanderingReckoner.class)
        newDeck.addMany(2, WanderingReckoner.class)

        newDeck.addMany(1, CthulhuAncientLord.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(6)

        assertEquals(12,game.battlefield.size())
        assertEquals(0,game.exile.size())
        assertTrue(game.battlefield.any { c -> c instanceof WanderingReckoner || c instanceof CthulhuAncientLord })
        assertEquals(0, game.battlefield.find { c -> c instanceof CthulhuAncientLord }.getCounter(Counter.TIME))
        assertFalse(game.playerWin)
    }

    void testCthulhuAncientLordRemoveCounter() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(1, CthulhuAncientLord.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(2, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(3,game.battlefield.size())
        assertEquals(1,game.exile.size())
        assertTrue(game.battlefield.any { c -> c instanceof WanderingReckoner })
        assertEquals(7, game.exile.find { c -> c instanceof CthulhuAncientLord }.getCounter(Counter.TIME))
        assertFalse(game.playerWin)
    }
}
