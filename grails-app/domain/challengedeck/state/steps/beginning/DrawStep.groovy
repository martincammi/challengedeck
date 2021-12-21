package challengedeck.state.steps.beginning

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.BeginningPhase
import challengedeck.state.phases.PhaseState
import challengedeck.state.phases.StepState

class DrawStep implements StepState {

    static DrawStep instance
    PhaseState phaseState

    private DrawStep(){
        phaseState = BeginningPhase.getInstance()
    }

    static StepState getInstance(PhaseState phaseState){
        if(instance == null){
            instance = new DrawStep()
            instance.phaseState = phaseState
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(DrawStep.getInstance())
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.DRAW]
    }

    List<CardType> castableTypes(){
        return [CardType.INSTANT]
    }
}
