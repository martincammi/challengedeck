package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.cards.Counter
import challengedeck.cards.Creature
import challengedeck.decks.cthulhudeck.InnsmouthOffspring
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class InnsmouthOffspringSpec extends SingleGame {

    void testInnsmouthOffspringOnly() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, InnsmouthOffspring.class)
        newDeck.addMany(1, InnsmouthOffspring.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertFalse(game.battlefield.any { c -> c.hasCounter(Counter.FISH)})
    }

    void testInnsmouthOffspringOnlySacrifice() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, InnsmouthOffspring.class)
        newDeck.addMany(1, InnsmouthOffspring.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertTrue(game.battlefield.isEmpty())

    }

    void testInnsmouthOffspringOnlyCreature() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, InnsmouthOffspring.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertTrue(game.battlefield.find { c -> c instanceof WanderingReckoner }.counters.get(Counter.FISH).equals(1))

    }

    void testTwoInnsmouthOffspringCreaturesAllFishCounters() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, WanderingReckoner.class)
        newDeck.addMany(2, InnsmouthOffspring.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertTrue(game.battlefield.findAll { c -> c instanceof WanderingReckoner }.every{ c -> c.hasCounter(Counter.FISH) })
    }

    void testTwoInnsmouthOffspringCreaturesNoFishCounter() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, InnsmouthOffspring.class)
        newDeck.addMany(1, InnsmouthOffspring.class)
        newDeck.addMany(2, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertFalse(game.battlefield.findAll { c -> c instanceof WanderingReckoner }.every{ c -> c.hasCounter(Counter.FISH) })
    }

    void testInnsmouthOffspringCreature() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, InnsmouthOffspring.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertTrue(game.battlefield.find { c -> c instanceof Creature}.hasCounter(Counter.FISH))
    }
}
