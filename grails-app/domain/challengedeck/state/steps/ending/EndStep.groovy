package challengedeck.state.steps.ending

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.StepState

class EndStep implements StepState {

    static EndStep instance

    private EndStep(){
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new EndStep()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(CleanupStep.getInstance())
        println "- " + CleanupStep.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.END_OF_TURN]
    }

    List<CardType> castableTypes(){
        return [CardType.INSTANT]
    }
}
