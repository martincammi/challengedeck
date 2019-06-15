package challengedeck

import challengedeck.cards.Card
import challengedeck.decks.cthulhudeck.InnsmouthOffspring
import challengedeck.decks.cthulhudeck.NightLurker
import challengedeck.decks.cthulhudeck.TheCallOfCthulhu
import challengedeck.decks.cthulhudeck.TheMadnessFromTheSea
import challengedeck.decks.cthulhudeck.WanderingReckoner
import junit.framework.TestCase

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
//@TestFor(CthulhuGame)
class CthulhuGameSpec extends SingleGame {

    void testEnterTheBattlefield() {

        CthulhuGame game = getSingleGame()

        Deck newDeck = new Deck();
        newDeck.addMany(3, NightLurker.class)
        newDeck.addMany(5, WanderingReckoner.class)
        newDeck.addMany(2, TheMadnessFromTheSea.class)
        newDeck.addMany(2, InnsmouthOffspring.class)
        newDeck.addMany(2, TheCallOfCthulhu.class)

        game.cthulhuDeck = newDeck
        game.start()
        assertEquals(10,game.battlefield.size())
    }

    /**
     * Results: Cthulhu win turns: 6,6,8,5,5,5,10,5,10,12 (Average: 7.2)
     * Results: Cthulhu win turns: 8,7,10,6,10,8,3,8,8,6 (Average: 7.4)
     * Results: Cthulhu win turns: 6,7,7,6,4,7,5,4,8,6 (Average: 6)
     * Results: Cthulhu win turns: 4,6,7,11,8,6,7,7,11,5 (Average: 7.2)
     * Results: Cthulhu win turns: 11,6,4,4,7,8,5,6,12,3 (Average: 6.6)
     * Average Cthulhu win turns: 7
     */
    void testCthulhuDeckWinsTurns(){

        CthulhuGame game
        List<Integer> cthulhuTurns = new ArrayList<>()
        Integer cthulhuWins = 0
        int games = 10
        for (int i =0; i < games; i++){
            game = getSingleGame()
            game.start()
            cthulhuTurns.add(game.cthulhuTurn)
            if(game.cthuluWin) { cthulhuWins++}
        }

        println "Results: Cthulhu win turns: " + cthulhuTurns.join(",") + " (Average: " + cthulhuTurns.sum()/cthulhuTurns.size() + ")"

        assertEquals(games,cthulhuWins)
    }
}
