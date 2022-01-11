package challengedeck.state.steps.main

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.Main1Phase
import challengedeck.state.phases.PhaseState
import challengedeck.state.phases.StepState
import challengedeck.state.steps.ending.EndStep

class Main2Step implements StepState {

    static Main2Step instance
    PhaseState phaseState

    private Main2Step(){
        phaseState = Main1Phase.getInstance()
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new Main2Step()
        }
        return instance
    }

    void next(DualGame dualGame) {
        dualGame.setStepState(EndStep.getInstance())
        println "- " + EndStep.class.getSimpleName()
    }

    @Override
    List<Trigger> getTriggers() {
        return [Trigger.BEGINNING_MAIN_PHASE_2]
    }

    List<CardType> castableTypes(){
        return Arrays.asList(CardType.values())
    }
}
