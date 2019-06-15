package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.cards.CreatureToken

class ActionCreateCultistToken extends Action {

    void resolve(CthulhuGame game){
        CreatureToken token = new CreatureToken("Cultist",
                          [CardType.CREATURE],
                          [CardSubType.CULTIST],
                          [], 1,1)
        game.putOnBattlefield(token)
    }

    static constraints = {
    }
}
