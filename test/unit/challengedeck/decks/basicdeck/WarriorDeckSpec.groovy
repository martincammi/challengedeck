package challengedeck.decks.basicdeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.cards.Mountain
import challengedeck.decks.cthulhudeck.MysticCaller
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class WarriorDeckSpec extends TestCase {

    void testBasicDeck() {

        CthulhuGame game = new CthulhuGame()

        Deck newDeck = new Deck()
        newDeck.addMany(1, MysticCaller.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(20,game.playerLife)
    }

    void testWarriorDeckOneMountain() {

        CthulhuGame game = new CthulhuGame()

        Deck newDeck = new Deck();
        newDeck.addMany(2, Mountain.class)
        newDeck.addMany(5, RedWarrior.class)

        newDeck.addMany(1, RedWarrior.class)

        game.player.deck = newDeck
        game.start(1)

        assertEquals(1, game.battlefield.count { it instanceof Mountain})
        assertTrue(game.battlefield.any { it instanceof Mountain})
    }

    void testWarriorDeckTwoMountainsOneWarrior() {

        CthulhuGame game = new CthulhuGame()

        Deck newDeckCthulhu = new Deck();
        newDeckCthulhu.addMany(10, WanderingReckoner.class)

        Deck newDeck = new Deck();
        newDeck.addMany(2, Mountain.class)
        newDeck.addMany(5, RedWarrior.class)

        newDeck.addMany(2, RedWarrior.class)

        game.player.deck = newDeck
        game.cthulhuDeck = newDeckCthulhu
        game.start(2)

        assertEquals(2, game.battlefield.count { it instanceof Mountain})
        assertEquals(1, game.battlefield.count { it instanceof RedWarrior})
    }

    void testWarriorDeckTwoMountainsTwoWarriors() {

        CthulhuGame game = new CthulhuGame()

        Deck newDeckCthulhu = new Deck();
        newDeckCthulhu.addMany(10, WanderingReckoner.class)

        Deck newDeck = new Deck();
        newDeck.addMany(2, Mountain.class)
        newDeck.addMany(5, RedWarrior.class)

        newDeck.addMany(3, RedWarrior.class)

        game.player.deck = newDeck
        game.cthulhuDeck = newDeckCthulhu
        game.start(3)

        assertEquals(2, game.battlefield.count { it instanceof Mountain})
        assertEquals(2, game.battlefield.count { it instanceof RedWarrior})
    }

    void testWarriorDeckThreeMountainsTwoWarriors() {

        CthulhuGame game = new CthulhuGame()

        Deck newDeckCthulhu = new Deck();
        newDeckCthulhu.addMany(10, WanderingReckoner.class)

        Deck newDeck = new Deck();
        newDeck.addMany(3, Mountain.class)
        newDeck.addMany(4, RedWarrior.class)
        newDeck.addMany(3, RedWarrior.class)

        game.player.deck = newDeck
        game.cthulhuDeck = newDeckCthulhu
        game.start(3)

        assertEquals(3, game.battlefield.count { it instanceof Mountain})
        assertEquals(2, game.battlefield.count { it instanceof RedWarrior})
    }

    void testWarriorDeckFourMountainsFourWarriors() {

        CthulhuGame game = new CthulhuGame()

        Deck newDeckCthulhu = new Deck();
        newDeckCthulhu.addMany(10, WanderingReckoner.class)

        Deck newDeck = new Deck();
        newDeck.addMany(4, Mountain.class)
        newDeck.addMany(3, RedWarrior.class)

        newDeck.addMany(10, RedWarrior.class)

        game.player.deck = newDeck
        game.cthulhuDeck = newDeckCthulhu
        game.start(4)

        assertEquals(4, game.battlefield.count { it instanceof Mountain})
        assertEquals(4, game.battlefield.count { it instanceof RedWarrior})
    }

    /**
     * Results: Cthulhu: 92, basicDeck: 8
     * Results: Cthulhu: 93, basicDeck: 7
     * Results: Cthulhu: 96, basicDeck: 4
     * Results: Cthulhu: 93, basicDeck: 7
     * Results: Cthulhu: 91, basicDeck: 9
     * Average Cthulhu wins: 93%
     * Average basicDeck-alwaysAttacking wins: 7%
     *
     * Necronomicón included
     * Results: Cthulhu: 99, basicDeck: 1
     * Results: Cthulhu: 98, basicDeck: 2
     * Results: Cthulhu: 96, basicDeck: 4
     * Results: Cthulhu: 94, basicDeck: 6
     * Results: Cthulhu: 92, basicDeck: 8
     * Average Cthulhu wins: 96%
     * Average basicDeck-alwaysAttacking wins: 4%
     * Average on 1000: 94/6%
     *
     * Cthulhu Idol included
     * Results: Cthulhu: 99, basicDeck: 1
     * Results: Cthulhu: 94, basicDeck: 6
     * Results: Cthulhu: 98, basicDeck: 2
     * Results: Cthulhu: 93, basicDeck: 7
     * Results: Cthulhu: 93, basicDeck: 7
     * Average Cthulhu wins: 95%
     * Average basicDeck-alwaysAttacking wins: 5%
     * Average on 1000: 95/4%
     *
     * Nyarlaphotep, The Black Man included
     * Results: Cthulhu: 99, basicDeck: 1
     * Results: Cthulhu: 98, basicDeck: 2
     * Results: Cthulhu: 100, basicDeck: 0
     * Results: Cthulhu: 98, basicDeck: 2
     * Results: Cthulhu: 97, basicDeck: 3
     * Average Cthulhu wins: 98%
     * Average basicDeck-alwaysAttacking wins:% 2
     * Average on 1000: 97/3%
     *
     * Nyarlaphotep, The Crawling Chaos
     * Results: Cthulhu: 99, basicDeck: 1
     * Results: Cthulhu: 99, basicDeck: 1
     * Results: Cthulhu: 98, basicDeck: 2
     * Results: Cthulhu: 96, basicDeck: 4
     * Results: Cthulhu: 97, basicDeck: 3
     * Average Cthulhu wins: 98%
     * Average basicDeck-alwaysAttacking wins:% 2
     * Average on 1000: 98/2%
     *
     * Preparing the Coming
     * Results: Cthulhu: 98, basicDeck: 2
     * Results: Cthulhu: 93, basicDeck: 7
     * Results: Cthulhu: 93, basicDeck: 7
     * Results: Cthulhu: 95, basicDeck: 5
     * Results: Cthulhu: 97, basicDeck: 3
     * Average Cthulhu wins: 96%
     * Average basicDeck-alwaysAttacking wins:% 4
     * Average on 1000: 97/3%
     *
     * Improved Simple deck to cast as much as possible on each turn
     * Average on 100: 86/14%
     * Average on 1000: 84/16%
     *
     * Added Cthulhu
     * Average on 100: 89/11%
     * Average on 1000: 88/12%
     */
    void testWarriorDeckWins(){

        CthulhuGame game
        Integer cthuluWins = 0
        Integer warriorDeckWins = 0
        int games = 100
        for (int i =0; i < games; i++){
            game = new CthulhuGame()
            game.start()
            if(game.cthuluWin){ cthuluWins++ }
            if(game.playerWin){ warriorDeckWins++ }
        }

        println "Results: Cthulhu: " + cthuluWins + ", basicDeck: " + warriorDeckWins

        assertTrue(true)
    }
}
