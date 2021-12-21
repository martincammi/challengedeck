package challengedeck.state.phases

import challengedeck.DualGame

class Main1Phase implements PhaseState {

    static Main1Phase instance
    StepState stepState

    private Main1Phase(){}

    static PhaseState getInstance(){
        if(instance == null){
            instance = new Main1Phase()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setPhaseState(CombatPhase.getInstance())
    }
}
