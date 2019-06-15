package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.cards.Creature
import challengedeck.decks.cthulhudeck.BrownJenkin
import challengedeck.decks.cthulhudeck.TheCallOfCthulhu
import challengedeck.decks.cthulhudeck.TheMadnessFromTheSea
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class BrownJenkinSpec extends SingleGame {

    void testBrownJenkinOnly() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, BrownJenkin.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(5,game.player.hand.size())
    }

    void testBrownJenkinCreature() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, BrownJenkin.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(6,game.player.hand.size())
    }

    void testBrownJenkinDies() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, BrownJenkin.class)

        game.cthulhuDeck = newDeck
        game.start(1)
        Creature brownJenkin = game.battlefield.first()
        game.sacrifice(brownJenkin)
        game.resolveStack()

        assertEquals(6,game.player.hand.size())
    }

    void testBrownJenkinNonCombatDamage() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, BrownJenkin.class)
        newDeck.addMany(1, TheMadnessFromTheSea.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(5,game.player.hand.size())
    }
}
