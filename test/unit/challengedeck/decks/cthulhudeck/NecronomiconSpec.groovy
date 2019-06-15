package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import grails.test.mixin.TestFor
import junit.framework.TestCase
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class NecronomiconSpec extends SingleGame {

    void testNecronomiconCreatures() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, WanderingReckoner.class)

        newDeck.addMany(1, AwakingTremors.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(1, Necronomicon.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(3)

        assertNotNull(game.battlefield.find { c -> c instanceof WanderingReckoner})
        assertEquals(3,game.battlefield.size())
    }

    void testNecronomiconExileCard() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(1, IntangibleNightmares.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(1, Necronomicon.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(2,game.battlefield.findAll { c -> c instanceof WanderingReckoner}.size())
        assertEquals(1,game.exile.size())
        assertNotNull(game.exile.find { c -> c instanceof IntangibleNightmares})
    }

    void testNecronomiconLeavesBattlefield() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(2, WanderingReckoner.class)

        newDeck.addMany(1, Necronomicon.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(2)
        game.sacrifice(game.battlefield.find { c -> c instanceof Necronomicon})
        game.resolveStack()

        assertEquals(0,game.battlefield.findAll { c -> c instanceof WanderingReckoner}.size())
        assertEquals(0,game.battlefield.size())
        assertEquals(0,game.exile.size())
        assertEquals(4,game.graveyard.size())
    }
}
