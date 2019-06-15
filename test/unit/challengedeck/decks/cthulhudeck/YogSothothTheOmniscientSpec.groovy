package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class YogSothothTheOmniscientSpec extends SingleGame {

    void testYogSothothTheOmniscientCreatureHorror() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, YogSothothTheOmniscient.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(3, game.battlefield.find { c -> c instanceof WanderingReckoner}.toughness)
        assertEquals(2, game.battlefield.find { c -> c instanceof WanderingReckoner}.power)
        assertEquals(6, game.battlefield.find { c -> c instanceof YogSothothTheOmniscient}.toughness)
        assertEquals(6, game.battlefield.find { c -> c instanceof YogSothothTheOmniscient}.power)
    }

    void testYogSothothTheOmniscientCreatureNonHorror() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, YogSothothTheOmniscient.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(2, game.battlefield.find { c -> c instanceof MysticCaller}.toughness)
        assertEquals(2, game.battlefield.find { c -> c instanceof MysticCaller}.power)
        assertEquals(6, game.battlefield.find { c -> c instanceof YogSothothTheOmniscient}.toughness)
        assertEquals(6, game.battlefield.find { c -> c instanceof YogSothothTheOmniscient}.power)
    }

    void testYogSothothTheOmniscientLoseLife() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, YogSothothTheOmniscient.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(2, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(13, game.playerLife)
    }
}
