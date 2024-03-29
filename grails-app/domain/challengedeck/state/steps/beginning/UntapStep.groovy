package challengedeck.state.steps.beginning

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.BeginningPhase
import challengedeck.state.phases.PhaseState
import challengedeck.state.phases.StepState

class UntapStep implements StepState {

    static UntapStep instance
    PhaseState phaseState

    private UntapStep(){
        phaseState = BeginningPhase.getInstance()
    }

    static StepState getInstance(){
        if(instance == null){
            instance = new UntapStep()
        }
        return instance
    }


    void next(DualGame dualGame) {
        dualGame.setStepState(UpkeepStep.getInstance())
        println "- " + UpkeepStep.class.getSimpleName()
    }

    /**
     * Any state-based effects or triggered abilities that happen during untap step are delayed until the upkeep step.
     * @return
     */
    List<Trigger> getTriggers(){
        return [Trigger.UNTAP_STEP]
    }

/**
     * On the untap step players can't cast spells or abilities
     * @return
     */
    List<CardType> castableTypes(){
        return []
    }
}
