package challengedeck.state.steps

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.StepState
import challengedeck.state.steps.beginning.UntapStep

/**
 * This is a fictional step to initiate the first StepState
 */
class InitialStep implements StepState {

    static InitialStep instance

    private InitialStep(){
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new InitialStep()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(UntapStep.getInstance())
        println "- " + UntapStep.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.DRAW]
    }

    List<CardType> castableTypes(){
        return [CardType.INSTANT]
    }
}
