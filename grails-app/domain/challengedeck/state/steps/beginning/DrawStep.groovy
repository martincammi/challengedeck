package challengedeck.state.steps.beginning

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.BeginningPhase
import challengedeck.state.phases.PhaseState
import challengedeck.state.phases.StepState
import challengedeck.state.steps.main.Main1Step

class DrawStep implements StepState {

    static DrawStep instance
    PhaseState phaseState

    private DrawStep(){
        phaseState = BeginningPhase.getInstance()
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new DrawStep()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(Main1Step.getInstance())
        println "- " + Main1Step.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.DRAW]
    }

    List<CardType> castableTypes(){
        return [CardType.INSTANT]
    }
}
