package challengedeck.state.steps.combat

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.StepState

class DeclareAttackersStep implements StepState {

    static DeclareAttackersStep instance

    private DeclareAttackersStep(){
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new DeclareAttackersStep()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(DeclareBlockersStep.getInstance())
        println "- " + DeclareBlockersStep.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.DECLARE_ATTACKERS]
    }

    List<CardType> castableTypes(){
        return [CardType.INSTANT]
    }
}
