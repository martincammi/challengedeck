package challengedeck

import challengedeck.cards.Creature
import challengedeck.cards.Strategy

class AlwaysAttackStrategy implements Strategy {

    static constraints = {
    }

    @Override
    void onAttack(CthulhuGame game) {
        game.battlefield.findAll{ c -> c instanceof Creature && game.isActivePlayer(c.owner)}.each { Creature c -> c.shouldAttack = true}
    }
}
