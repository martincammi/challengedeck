package challengedeck.cards

import challengedeck.CthulhuGame
import challengedeck.DualGame
import grails.util.Pair

interface Strategy {

    void onAttack(CthulhuGame game)

    Boolean needMulligan(List<Card> cards)

    Pair<Card, List<Card>> nextCardToPlay(DualGame dualGame, Player player)

}