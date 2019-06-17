package challengedeck.decks.cthulhudeck

import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class NyarlaphotepTheBlackManSpec extends SingleGame {

    void testNyarlaphotepTheBlackManCreature() {

        CthulhuGame game =  getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, NyarlaphotepTheBlackMan.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(3,game.battlefield.find { it instanceof WanderingReckoner}.getPower())
        assertEquals(3,game.battlefield.find { it instanceof WanderingReckoner}.getToughness())
    }

    void testNyarlaphotepTheBlackManCreatureAttacks() {

        CthulhuGame game =  getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, NyarlaphotepTheBlackMan.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(2, TheCallOfCthulhu.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(16,game.playerLife)
    }

    void testNyarlaphotepTheBlackManLeavesBattlefield() {

        CthulhuGame game =  getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, NyarlaphotepTheBlackMan.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(1, AwakingTremors.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(1, MysticCaller.class)
        newDeck.addMany(1, YogSothothTheOmniscient.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(1,game.battlefield.size())
        assertTrue(game.battlefield.first().cardTypes.contains(CardType.LEGENDARY))
        assertTrue(game.battlefield.first().cardTypes.contains(CardType.CREATURE))
        assertNotNull(game.cthulhuDeck.cards.find { it instanceof MysticCaller})
    }
}
