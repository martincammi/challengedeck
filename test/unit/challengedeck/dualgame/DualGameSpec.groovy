package challengedeck.dualgame

import challengedeck.Deck
import challengedeck.DualGame
import challengedeck.cards.Mountain
import challengedeck.decks.cthulhudeck.WanderingReckoner
import challengedeck.strategies.MulliganStrategy
import junit.framework.TestCase

class DualGameSpec extends TestCase {

    void testMulliganAllLands() {

        DualGame game = DualGame.getInstance()

        Deck deck1 = new Deck()
        deck1.addMany(60, Mountain.class)
        game.player1.deck = deck1

        Deck deck2 = new Deck()
        deck2.addMany(60, Mountain.class)
        game.player2.deck = deck2

        game.start()
        assertEquals(DualGame.DEFAULT_INITIAL_CARDS,game.player1.hand.size())
        assertEquals(DualGame.DEFAULT_INITIAL_CARDS,game.player2.hand.size())
    }

    void testMulliganNoLands() {

        DualGame game = DualGame.getInstance()

        Deck deck1 = new Deck()
        deck1.addMany(60, WanderingReckoner.class)
        game.player1.deck = deck1

        Deck deck2 = new Deck()
        deck2.addMany(60, WanderingReckoner.class)
        game.player2.deck = deck2

        game.start(game.NO_TURNS)
        assertEquals(MulliganStrategy.MIN_CARDS_STOP_MULLIGAN,game.player1.hand.size())
        assertEquals(MulliganStrategy.MIN_CARDS_STOP_MULLIGAN,game.player2.hand.size())
    }

    void testMulliganSomeLands() {

        DualGame game = DualGame.getInstance()

        Deck deck1 = new Deck()
        deck1.addMany(5, WanderingReckoner.class)
        deck1.addMany(2, Mountain.class)
        deck1.addMany(53, WanderingReckoner.class)
        game.player1.deck = deck1

        Deck deck2 = new Deck()
        deck2.addMany(6, WanderingReckoner.class)
        deck2.addMany(1, Mountain.class)
        deck1.addMany(53, WanderingReckoner.class)
        game.player2.deck = deck2

        game.start(game.NO_TURNS)
        assertEquals(7,game.player1.hand.size())
        assertEquals(MulliganStrategy.MIN_CARDS_STOP_MULLIGAN,game.player2.hand.size())
    }

    void testUpkeep() {

        DualGame game = DualGame.getInstance()

        Deck deck1 = new Deck()
        deck1.addMany(37, WanderingReckoner.class)
        deck1.addMany(23, Mountain.class)
        deck1.shuffle()
        game.player1.deck = deck1

        Deck deck2 = new Deck()
        deck2.addMany(37, WanderingReckoner.class)
        deck2.addMany(23, Mountain.class)
        deck2.shuffle()
        game.player2.deck = deck2

        game.start()
        assertEquals(22, game.turnCount)
    }

    void testSevenOrLessCardsInHandEndOfTurn(){

        DualGame game = DualGame.getInstance()

        Deck deck1 = new Deck()
        deck1.addMany(60, Mountain.class)
        game.player1.deck = deck1

        Deck deck2 = new Deck()
        deck2.addMany(60, Mountain.class)
        game.player2.deck = deck2

        game.start()
        assertEquals(DualGame.DEFAULT_INITIAL_CARDS,game.player1.hand.size())
        assertEquals(DualGame.DEFAULT_INITIAL_CARDS,game.player2.hand.size())
    }


}
