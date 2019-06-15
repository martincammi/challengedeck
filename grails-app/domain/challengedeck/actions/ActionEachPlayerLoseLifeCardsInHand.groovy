package challengedeck.actions

import challengedeck.Action
import challengedeck.CthulhuGame
import challengedeck.cards.Creature

class ActionEachPlayerLoseLifeCardsInHand extends Action {

    void resolve(CthulhuGame game){
        println "Yog Sothoth ability, player lose " + game.player.hand.size()  + " lifes"
        game.substractPlayerLife(game.player.hand.size())
    }

    static constraints = {
    }
}
