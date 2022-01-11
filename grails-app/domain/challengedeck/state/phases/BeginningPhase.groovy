package challengedeck.state.phases

import challengedeck.DualGame
import challengedeck.state.steps.beginning.UntapStep

class BeginningPhase implements PhaseState {

    static BeginningPhase instance

    private BeginningPhase(){
    }

    static PhaseState getInstance(){
        if(instance == null){
            instance = new BeginningPhase()
        }
        return instance
    }

    void next(DualGame dualGame) {
    }
}
