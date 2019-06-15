package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.cards.Counter
import challengedeck.cards.Creature
import challengedeck.cards.CreatureToken
import challengedeck.decks.cthulhudeck.CthulhuWorshiper
import challengedeck.decks.cthulhudeck.InnsmouthOffspring
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class CthulhuWorshiperSpec extends SingleGame {

    void testCthulhuWorshiperOnly() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(1, CthulhuWorshiper.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(3,game.battlefield.size())
        assertTrue(game.battlefield.any { c -> c instanceof CreatureToken })
        assertTrue(game.battlefield.any { c -> c.name.equals("Cultist")})
    }

    void testTwoCthulhuWorshiper() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(2, CthulhuWorshiper.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(4,game.battlefield.size())
        assertEquals(2,game.battlefield.findAll { c -> c instanceof CreatureToken }.size())
        assertEquals(2,game.battlefield.findAll { c -> c.name.equals("Cultist")}.size())
    }

    void testCthulhuWorshiperSacrifice() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(2, CthulhuWorshiper.class)  //First Turn 2 creatures, and put 2 tokens at end of turn. 4 Creatures total
        newDeck.addMany(2, CthulhuWorshiper.class)  //Second Turn 6 creatures, and put 4 tokens at end of turn. 10 Creatures total
        newDeck.addMany(2, WanderingReckoner.class) //Third Turn 12 creatures, sacrifice all the cultists. 2 Creatures total.

        game.cthulhuDeck = newDeck
        game.start(3)

        assertEquals(2,game.battlefield.size())
        assertEquals(2,game.battlefield.findAll { c -> c instanceof WanderingReckoner }.size())
    }

    void testCthulhuWorshiperInnsmouth() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(2, CthulhuWorshiper.class)  //First Turn 2 creatures, and put 2 tokens at end of turn. 4 Creatures total

        newDeck.addMany(1, InnsmouthOffspring.class)
        newDeck.addMany(1, WanderingReckoner.class) //Second Turn 5 creatures, and put 2 tokens at end of turn. 7 Creatures total one with fish counter.

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(8,game.battlefield.size())
        assertEquals(7,game.battlefield.findAll { c -> c instanceof Creature }.size())
        assertEquals(1,game.battlefield.findAll { c -> c.hasCounter(Counter.FISH) }.size())
    }

}
