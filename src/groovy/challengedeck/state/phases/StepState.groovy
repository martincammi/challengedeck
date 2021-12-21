package challengedeck.state.phases

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.cards.Card
import challengedeck.state.phases.PhaseState

interface StepState {

    void next(DualGame dualGame)

    List<Trigger> getTriggers()

    List<CardType> castableTypes()

}