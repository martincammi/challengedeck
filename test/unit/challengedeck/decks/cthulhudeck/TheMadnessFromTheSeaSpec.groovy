package challengedeck.decks.cthulhudeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.SingleGame
import challengedeck.decks.cthulhudeck.TheMadnessFromTheSea
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

class TheMadnessFromTheSeaSpec extends SingleGame {

    void testMadnessOnly() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, TheMadnessFromTheSea.class)

        game.cthulhuDeck = newDeck
        game.start()

        assertEquals(20, game.playerLife)
    }

    void testCreatureMadness() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, WanderingReckoner.class)
        newDeck.addMany(1, TheMadnessFromTheSea.class)

        game.cthulhuDeck = newDeck
        game.start()

        assertEquals(19, game.playerLife)
    }

    void testMadnessCreature() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, TheMadnessFromTheSea.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start()

        assertEquals(19, game.playerLife)
    }

    void testManyCreaturesMadness() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, WanderingReckoner.class)

        newDeck.addMany(1, WanderingReckoner.class)
        newDeck.addMany(1, TheMadnessFromTheSea.class)

        game.cthulhuDeck = newDeck
        game.start()

        assertEquals(17, game.playerLife)
    }

    void testMadnessManyCreatures() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, TheMadnessFromTheSea.class)
        newDeck.addMany(1, WanderingReckoner.class)

        newDeck.addMany(2, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start()

        assertEquals(19, game.playerLife)
    }

    void testTwoMadnessManyCreatures() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, WanderingReckoner.class)

        newDeck.addMany(2, TheMadnessFromTheSea.class)

        newDeck.addMany(2, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start()

        assertEquals(16, game.playerLife)
    }
}
