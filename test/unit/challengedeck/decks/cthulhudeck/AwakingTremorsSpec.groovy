package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.cards.Creature
import challengedeck.decks.cthulhudeck.AwakingTremors
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class AwakingTremorsSpec extends SingleGame {

    void testAwakingTremorsCreature() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, AwakingTremors.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertFalse(game.battlefield.any { c -> c instanceof Creature})
    }

    void testAwakingTremorsTwoCreatures() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, AwakingTremors.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(2, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(2, game.getAllCreatures().size())
    }
}
