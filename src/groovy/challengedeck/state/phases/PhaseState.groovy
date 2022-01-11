package challengedeck.state.phases

import challengedeck.DualGame

interface PhaseState {

    void next(DualGame dualGame)

}