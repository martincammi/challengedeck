package challengedeck.state.steps.combat

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.StepState

class CombatDamageStep implements StepState {

    static CombatDamageStep instance

    private CombatDamageStep(){
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new CombatDamageStep()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(EndOfCombatStep.getInstance())
        println "- " + EndOfCombatStep.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.ASSIGN_COMBAT_DAMAGE]
    }

    List<CardType> castableTypes(){
        return [CardType.INSTANT]
    }
}
