package challengedeck.state.steps.combat

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.StepState

class DeclareBlockersStep implements StepState {

    static DeclareBlockersStep instance

    private DeclareBlockersStep(){
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new DeclareBlockersStep()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(CombatDamageStep.getInstance())
        println "- " + CombatDamageStep.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.DECLARE_BLOCKERS]
    }

    List<CardType> castableTypes(){
        return [CardType.INSTANT]
    }
}
