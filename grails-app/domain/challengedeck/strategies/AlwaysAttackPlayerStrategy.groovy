package challengedeck.strategies

import challengedeck.CthulhuGame
import challengedeck.DualGame
import challengedeck.cards.Card
import challengedeck.cards.Creature
import challengedeck.cards.Player
import challengedeck.cards.Strategy
import grails.util.Pair

class AlwaysAttackPlayerStrategy implements Strategy {

    static constraints = {
    }

    @Override
    void onAttack(CthulhuGame game) {
        game.battlefield.findAll{ c -> c instanceof Creature && game.isActivePlayer(c.owner)}.each { Creature c -> c.shouldAttack = true}
    }

    @Override
    Boolean needMulligan(List<Card> cards) {
        return new MulliganStrategy().needMulligan(cards)
    }

    @Override
    Pair<Card, List<Card>> nextCardToPlay(DualGame dualGame, Player player) {
        return null
    }
}
