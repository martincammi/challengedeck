package challengedeck.cards

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class MountainSpec extends SingleGame {

    void testMountainOnly() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, Mountain.class)
        newDeck.addMany(1, Mountain.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(2, game.battlefield.size())
        assertTrue(game.battlefield.every { c -> c instanceof Mountain})
    }
}
