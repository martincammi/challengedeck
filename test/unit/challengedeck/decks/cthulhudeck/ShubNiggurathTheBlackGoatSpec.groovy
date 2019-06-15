package challengedeck.decks.cthulhudeck

import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.cards.Creature
import challengedeck.cards.CreatureToken

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class ShubNiggurathTheBlackGoatSpec extends SingleGame {

    void testShubNiggurathTheBlackGoatOnly() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, ShubNiggurathTheBlackGoat.class)
        newDeck.addMany(1, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(20,game.playerLife)
        assertEquals(1, game.battlefield.find { c -> c instanceof ShubNiggurathTheBlackGoat }.toughness)
        assertEquals(1, game.battlefield.find { c -> c instanceof ShubNiggurathTheBlackGoat }.power)
        assertEquals(2, game.battlefield.find { c -> c instanceof MysticCaller }.toughness)
        assertEquals(2, game.battlefield.find { c -> c instanceof MysticCaller }.power)
    }

    void testShubNiggurathTheBlackGoatCreatures() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, ShubNiggurathTheBlackGoat.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(20,game.playerLife)
        assertEquals(2, game.battlefield.find { c -> c instanceof ShubNiggurathTheBlackGoat }.toughness)
        assertEquals(2, game.battlefield.find { c -> c instanceof ShubNiggurathTheBlackGoat }.power)
        assertEquals(2, game.battlefield.find { c -> c instanceof WanderingReckoner }.toughness)
        assertEquals(2, game.battlefield.find { c -> c instanceof WanderingReckoner }.power)
    }

    void testShubNiggurathTheBlackGoatCreatures2() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, ShubNiggurathTheBlackGoat.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(2, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(2)

        assertEquals(15,game.playerLife)
        assertEquals(5, game.battlefield.size())
        assertNotNull(game.battlefield.find { c -> c instanceof CreatureToken })
        assertTrue(game.battlefield.find { c -> c instanceof CreatureToken }.cardSubTypes.contains(CardSubType.HORROR))

        assertEquals(3, game.battlefield.find { c -> c instanceof ShubNiggurathTheBlackGoat }.power)
        assertEquals(3, game.battlefield.find { c -> c instanceof ShubNiggurathTheBlackGoat }.toughness)
    }

    void testShubNiggurathTheBlackGoatCreatures3() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, ShubNiggurathTheBlackGoat.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(2, MysticCaller.class)

        game.cthulhuDeck = newDeck
        game.start(2)
        Creature darkYoung = game.battlefield.find { c -> c instanceof CreatureToken }
        game.sacrifice(darkYoung)

        assertEquals(15,game.playerLife)
        assertEquals(4, game.battlefield.size())
        assertNull(game.battlefield.find { c -> c instanceof CreatureToken })

        assertEquals(2, game.battlefield.find { c -> c instanceof ShubNiggurathTheBlackGoat }.getPower())
        assertEquals(2, game.battlefield.find { c -> c instanceof ShubNiggurathTheBlackGoat }.getToughness())
    }

    void testShubNiggurathTheBlackGoatLeaves() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, ShubNiggurathTheBlackGoat.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)
        Creature shubNiggurath = game.battlefield.find { c -> c instanceof ShubNiggurathTheBlackGoat }
        game.sacrifice(shubNiggurath)
        game.resolveStack()

        assertEquals(1, game.battlefield.size())
        assertEquals(10,game.player.hand.size())
    }
}
