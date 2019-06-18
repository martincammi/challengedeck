package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class PreparingTheComingSpec extends SingleGame {

    void testPreparingTheComingExile() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, WanderingReckoner.class)
        newDeck.addMany(1, PreparingTheComing.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(0,game.battlefield.size())
    }

    void testPreparingTheComingExileAndReturn() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, WanderingReckoner.class)
        newDeck.addMany(1, PreparingTheComing.class)

        newDeck.addMany(2, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(3)

        assertEquals(3,game.battlefield.size())
        assertTrue(game.battlefield.any { it instanceof WanderingReckoner})
    }
}
