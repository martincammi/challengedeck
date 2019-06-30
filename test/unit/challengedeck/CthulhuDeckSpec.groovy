package challengedeck

import challengedeck.cards.Card

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
class CthulhuDeckSpec extends SingleGame {

    void testCthulhuDeckWinsAlone() {

        CthulhuGame game
        Integer cthuluWins = 0
        int games = 100
        for (int i =0; i < games; i++){
            game = getSingleGame()
            game.start()
            if(game.cthuluWin){ cthuluWins++ }
        }

        println "Results: Cthulhu: " + cthuluWins + " against none"

        assertTrue(true)
    }

    void testCthulhuDeckStatistics() {

        CthulhuGame game
        Map<Integer, Integer> cthulhuWonTurns = new HashMap() //Turn it won, amount of times won on that turn
        Map<Integer, Integer> cthulhuWonLegendaries = new HashMap() //Turn it won, amount of legendary creatures played on all that turns
        int games = 100
        for (int i =0; i < games; i++){
            game = getSingleGame()
            game.start()
            Integer amountOfLegendaries = game.battlefield.count { it.cardTypes.contains(CardType.LEGENDARY) } +
                                          game.graveyard.count { it.cardTypes.contains(CardType.LEGENDARY) } +
                                          game.exile.count { it.cardTypes.contains(CardType.LEGENDARY) }
            if(cthulhuWonTurns.get(game.cthulhuTurn) == null){
                cthulhuWonTurns.put(game.cthulhuTurn,1)
                cthulhuWonLegendaries.put(game.cthulhuTurn,amountOfLegendaries)
            }else{
                cthulhuWonTurns.put(game.cthulhuTurn, cthulhuWonTurns.get(game.cthulhuTurn) + 1)
                cthulhuWonLegendaries.put(game.cthulhuTurn, cthulhuWonLegendaries.get(game.cthulhuTurn) + amountOfLegendaries)
            }
        }
        println "Cthulhu won on turns: " + cthulhuWonTurns
        println "Cthulhu Legend turns: " + cthulhuWonLegendaries

        assertTrue(true)
    }
}
