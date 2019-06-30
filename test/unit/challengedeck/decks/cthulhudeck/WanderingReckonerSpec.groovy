package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class WanderingReckonerSpec extends SingleGame {

    void testWanderingOnly() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start()

        assertEquals(WanderingReckoner.class, game.battlefield.first().class)
    }
}
