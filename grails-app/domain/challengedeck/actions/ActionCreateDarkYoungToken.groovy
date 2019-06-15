package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.cards.CreatureToken

class ActionCreateDarkYoungToken extends Action {

    void resolve(CthulhuGame game){
        CreatureToken token = new CreatureToken("Dark Young",
                          [CardType.CREATURE],
                          [CardSubType.HORROR],
                          [], 1,1)
        game.putOnBattlefield(token)
    }

    static constraints = {
    }
}
