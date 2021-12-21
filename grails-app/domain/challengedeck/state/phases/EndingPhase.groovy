package challengedeck.state.phases

import challengedeck.DualGame

class EndingPhase implements PhaseState {

    static EndingPhase instance
    StepState stepState

    private EndingPhase(){}

    static PhaseState getInstance(){
        if(instance == null){
            instance = new EndingPhase()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setPhaseState(BeginningPhase.getInstance())
    }
}
