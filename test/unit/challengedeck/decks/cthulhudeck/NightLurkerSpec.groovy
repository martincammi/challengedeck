package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.decks.cthulhudeck.NightLurker
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class NightLurkerSpec extends SingleGame {

    void testNightLurkerOnly() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, NightLurker.class)
        newDeck.addMany(2, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(NightLurker.class, game.battlefield.first().class)
        assertEquals(3,game.battlefield.size())
    }

    void testTwoNightLurker() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, NightLurker.class)
        newDeck.addMany(2, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(NightLurker.class, game.battlefield.first().class)
        assertEquals(4,game.battlefield.size())
    }
}
