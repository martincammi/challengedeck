package challengedeck.state.steps.main

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.Main1Phase
import challengedeck.state.phases.PhaseState
import challengedeck.state.phases.StepState
import challengedeck.state.steps.combat.BeginningCombatStep

class Main1Step implements StepState {

    static Main1Step instance
    PhaseState phaseState

    private Main1Step(){
        phaseState = Main1Phase.getInstance()
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new Main1Step()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(BeginningCombatStep.getInstance())
        println "- " + BeginningCombatStep.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.BEGINNING_MAIN_PHASE_1]
    }

    List<CardType> castableTypes(){
        return Arrays.asList(CardType.values())
    }
}
