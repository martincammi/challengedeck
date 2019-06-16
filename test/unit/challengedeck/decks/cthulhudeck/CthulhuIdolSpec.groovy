package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.cards.Card
import challengedeck.cards.CreatureToken
import challengedeck.decks.basicdeck.RedWarrior
import grails.test.mixin.TestFor
import junit.framework.TestCase
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class CthulhuIdolSpec extends SingleGame {

    void testCthulhuIdolCreatures() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(1, CthulhuIdol.class)
        newDeck.addMany(1, WanderingReckoner.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(3,game.battlefield.size())
        assertTrue(game.battlefield.any { c -> c instanceof MysticCaller})
    }

    void testCthulhuIdolLeavesBattlefield() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck()
        newDeck.addMany(1, CthulhuIdol.class)
        newDeck.addMany(1, WanderingReckoner.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(1)
        game.playerGraveyard.add(new RedWarrior())
        Card cthulhuIdol = game.battlefield.find { c -> c instanceof CthulhuIdol}
        game.sacrifice(cthulhuIdol)
        game.resolveStack()

        assertEquals(3,game.battlefield.size())
        assertFalse(game.battlefield.any { c -> c instanceof CthulhuIdol})
        assertTrue(game.battlefield.any { c -> c instanceof RedWarrior})
    }
}
