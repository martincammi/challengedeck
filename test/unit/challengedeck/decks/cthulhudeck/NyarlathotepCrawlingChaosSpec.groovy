package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.cards.Card
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class NyarlathotepCrawlingChaosSpec extends SingleGame {

    void testNyarlathotepCrawlingChaosHorrors() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, NyarlathotepCrawlingChaos.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(1,game.playerGraveyard.size())
    }

    void testNyarlathotepCrawlingChaosHorrors2() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, WanderingReckoner.class)

        newDeck.addMany(1, NyarlathotepCrawlingChaos.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(3,game.playerGraveyard.size())
    }

    void testNyarlathotepCrawlingChaosLeavesBattlefield() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, WanderingReckoner.class)

        newDeck.addMany(2, WanderingReckoner.class)

        newDeck.addMany(1, NyarlathotepCrawlingChaos.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(3)
        Card nyarlathotepCrawlingChaos = game.battlefield.find { it instanceof NyarlathotepCrawlingChaos }
        game.sacrifice(nyarlathotepCrawlingChaos)
        game.resolveStack()

        assertEquals(0,game.playerGraveyard.size())
    }
}
