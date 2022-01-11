package challengedeck.state.steps.combat

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.StepState
import challengedeck.state.steps.main.Main2Step

class EndOfCombatStep implements StepState {

    static EndOfCombatStep instance

    private EndOfCombatStep(){
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new EndOfCombatStep()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(Main2Step.getInstance())
        println "- " + Main2Step.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.END_OF_COMBAT_DAMAGE]
    }

    List<CardType> castableTypes(){
        return [CardType.INSTANT]
    }
}
