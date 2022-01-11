package challengedeck.state.phases

import challengedeck.DualGame

class CombatPhase implements PhaseState {

    static CombatPhase instance

    private CombatPhase(){}

    static PhaseState getInstance(){
        if(instance == null){
            instance = new CombatPhase()
        }
        return instance
    }

    void next(DualGame dualGame) {
    }
}
