package challengedeck.state.steps.ending

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.StepState
import challengedeck.state.steps.beginning.UntapStep

class CleanupStep implements StepState {

    static CleanupStep instance

    private CleanupStep(){
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new CleanupStep()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(UntapStep.getInstance())
        println "- " + UntapStep.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.CLEANUP]
    }

    List<CardType> castableTypes(){
        return [] // Normally, no player receives priority during the cleanup step, so no spells can be cast and no abilities can be activated.
    }
}
