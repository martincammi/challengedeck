package challengedeck.state.phases

import challengedeck.DualGame

class Main2Phase implements PhaseState {

    static Main2Phase instance
    StepState stepState

    private Main2Phase(){}

    static PhaseState getInstance(){
        if(instance == null){
            instance = new Main2Phase()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setPhaseState(EndingPhase.getInstance())
    }
}
