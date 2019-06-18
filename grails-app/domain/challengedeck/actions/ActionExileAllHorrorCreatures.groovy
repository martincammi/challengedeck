package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.cards.Creature

class ActionExileAllHorrorCreatures extends Action {

    void resolve(CthulhuGame game){
        game.battlefield.clone().each { c ->
            if(c.cardSubTypes.contains(CardSubType.HORROR) && c instanceof Creature){
                game.leaveTheBattlefield(c)
                game.exile.add(c)
                c.exiledBy = ability.card
            }
        }
    }

    static constraints = {
    }
}
