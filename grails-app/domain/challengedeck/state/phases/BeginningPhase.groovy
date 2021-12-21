package challengedeck.state.phases

import challengedeck.DualGame
import challengedeck.state.steps.beginning.UntapStep

class BeginningPhase implements PhaseState {

    static BeginningPhase instance
    StepState stepState

    private BeginningPhase(){
        stepState = UntapStep.getInstance(this)
    }

    static PhaseState getInstance(){
        if(instance == null){
            instance = new BeginningPhase()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setPhaseState(Main1Phase.getInstance())
    }
}
