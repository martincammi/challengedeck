package challengedeck

import challengedeck.cards.Card
import challengedeck.decks.cthulhudeck.CthulhuAncientLord

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

    void dtestCthulhuDeckStatistics() {

        CthulhuGame game
        Map<Integer, Integer> cthulhuWonTurns = new HashMap() //Turn it won, amount of times won on that turn
        Map<Integer, Integer> cthulhuWonLegendaries = new HashMap() //Turn it won, amount of legendary creatures played on all that turns

        int games = 100
            cthulhuWonTurns.clear()
            cthulhuWonLegendaries.clear()
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

    void dtestCthulhuDeckStatisticsAverage() {

        CthulhuGame game
        Map<Integer, Integer> cthulhuWonTurns = new HashMap() //Turn it won, amount of times won on that turn
        Map<Integer, Integer> cthulhuWonLegendaries = new HashMap() //Turn it won, amount of legendary creatures played on all that turns

        Map<Integer, Double> cthulhuWonTurnsAverage = new HashMap() //Turn it won, amount of times won on that turn Average
        Map<Integer, Double> cthulhuWonLegendariesAverage = new HashMap() //Turn it won, amount of legendary creatures played on all that turns Average

        Map<Integer, Double> cthulhuAppeared = new HashMap()
        Map<Integer, Double> cthulhuAppearedAverage = new HashMap()

        int iterations = 100
        int games = 100

        for (int j =0; j < iterations; j++){

            cthulhuWonTurns.clear()
            cthulhuWonLegendaries.clear()
            cthulhuAppeared.clear()
            for (int i =0; i < games; i++){
                game = getSingleGame()
                game.start()
                Integer amountOfLegendaries = game.battlefield.count { it.cardTypes.contains(CardType.LEGENDARY) } +
                        game.graveyard.count { it.cardTypes.contains(CardType.LEGENDARY) } +
                        game.exile.count { it.cardTypes.contains(CardType.LEGENDARY) }
                Boolean cthulhuCast = game.battlefield.any { it instanceof CthulhuAncientLord}

                if(cthulhuAppeared.get(game.cthulhuTurn) == null){
                    cthulhuAppeared.put(game.cthulhuTurn,0)
                }

                if(cthulhuCast){
                    cthulhuAppeared.put(game.cthulhuTurn,cthulhuAppeared.get(game.cthulhuTurn) + 1)
                }

                if(cthulhuWonTurns.get(game.cthulhuTurn) == null){
                    cthulhuWonTurns.put(game.cthulhuTurn,1)
                    cthulhuWonLegendaries.put(game.cthulhuTurn,amountOfLegendaries / games)
                }else{
                    cthulhuWonTurns.put(game.cthulhuTurn, cthulhuWonTurns.get(game.cthulhuTurn) + 1)
                    cthulhuWonLegendaries.put(game.cthulhuTurn, cthulhuWonLegendaries.get(game.cthulhuTurn) + (amountOfLegendaries/games))
                }
            }

            println "Checking condition " + (cthulhuWonTurns.values().sum() == games ? "OK" : "WRONG")
            println "Cthulhu won on turns: " + cthulhuWonTurns
            println "Cthulhu Legend turns: " + cthulhuWonLegendaries

            for(Integer turn: cthulhuWonTurns.keySet()){

                if(!cthulhuAppearedAverage.get(turn)){
                    cthulhuAppearedAverage.put(turn, 0)
                }

                if(!cthulhuWonTurnsAverage.get(turn)){
                    cthulhuWonTurnsAverage.put(turn, 0)
                }

                if(!cthulhuWonLegendariesAverage.get(turn)){
                    cthulhuWonLegendariesAverage.put(turn, 0)
                }

                cthulhuWonTurnsAverage.put(turn,(cthulhuWonTurnsAverage.get(turn) + (cthulhuWonTurns.get(turn)) / iterations))
                cthulhuWonLegendariesAverage.put(turn,(cthulhuWonLegendariesAverage.get(turn) + (cthulhuWonLegendaries.get(turn)) / iterations))
                cthulhuAppearedAverage.put(turn,(cthulhuAppearedAverage.get(turn) + (cthulhuAppeared.get(turn) / iterations)))
            }
        }

        println "Checking condition " + (cthulhuWonTurnsAverage.values().sum() == games ? "OK" : "WRONG")
        println "Cthulhu won average: " + cthulhuWonTurnsAverage
        println "Cthulhu Legend average: " + cthulhuWonLegendariesAverage
        println "Cthulhu Appeared average: " + cthulhuAppearedAverage

        assertTrue(true)
    }
}
