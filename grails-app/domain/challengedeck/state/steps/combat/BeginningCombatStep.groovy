package challengedeck.state.steps.combat

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.StepState

class BeginningCombatStep implements StepState {

    static BeginningCombatStep instance

    private BeginningCombatStep(){
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new BeginningCombatStep()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(DeclareAttackersStep.getInstance())
        println "- " + DeclareAttackersStep.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.BEGINNING_OF_COMBAT]
    }

    List<CardType> castableTypes(){
        return [CardType.INSTANT]
    }
}
