package challengedeck.actions

import challengedeck.Action
import challengedeck.CardSubType
import challengedeck.CthulhuGame
import challengedeck.Trigger
import challengedeck.cards.Card
import challengedeck.cards.Counter
import challengedeck.decks.cthulhudeck.CthulhuAncientLord

class ActionSuspendItWithXMinusHorrorTimeCounters extends Action {

    static constraints = {
    }

    @Override
    void resolve(CthulhuGame game) {
        Card cthulhu = game.revealed.find { it instanceof CthulhuAncientLord }
        if(cthulhu){
            game.moveTo(game.REVEALED, game.EXILE, cthulhu)
            Integer amountOfHorrorCreatures = game.battlefield.count { it.cardSubTypes.contains(CardSubType.HORROR)}
            cthulhu.setCounter(Counter.TIME, 10 - amountOfHorrorCreatures)
            game.stepsAbilities.add(cthulhu.abilities.find { it.trigger == Trigger.CHECK_SUSPEND})
            game.triggersToStack(Trigger.CHECK_SUSPEND)
        }
    }
}
