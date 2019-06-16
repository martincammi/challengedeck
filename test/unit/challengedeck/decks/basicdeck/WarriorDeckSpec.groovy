package challengedeck.decks.basicdeck

import challengedeck.CthulhuGame
import challengedeck.Deck
import challengedeck.decks.cthulhudeck.MysticCaller
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class WarriorDeckSpec extends TestCase {

    void testBasicDeck() {

        CthulhuGame game = new CthulhuGame()

        Deck newDeck = new Deck();
        newDeck.addMany(1, MysticCaller.class)
        newDeck.addMany(1, WanderingReckoner.class)

        game.cthulhuDeck = newDeck
        game.start(1)

        assertEquals(20,game.playerLife)
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
     */
    void dtestWarriorDeckWins(){

        CthulhuGame game
        Integer cthuluWins = 0
        Integer warriorDeckWins = 0
        int games = 1000
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
