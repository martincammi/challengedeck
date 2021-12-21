package challengedeck.state.steps.beginning

import challengedeck.CardType
import challengedeck.DualGame
import challengedeck.Trigger
import challengedeck.state.phases.BeginningPhase
import challengedeck.state.phases.PhaseState
import challengedeck.state.phases.StepState
import challengedeck.state.steps.main.Main1Step

class UpkeepStep implements StepState {

    static UpkeepStep instance
    PhaseState phaseState

    private UpkeepStep(){
        phaseState = BeginningPhase.getInstance()
    }

    static StepState getInstance(PhaseState phaseState){
        if(instance == null){
            instance = new UpkeepStep()
            instance.phaseState = phaseState
        }
        return instance
    }


    void next(DualGame dualGame) {
        dualGame.setStepState(Main1Step.getInstance())
    }

    /**
     * Any state-based effects or triggered abilities that happen during untap step are delayed until the upkeep step.
     * @return
     */
    List<Trigger> getTriggers(){
        return [Trigger.BEGINNING_UPKEEP, Trigger.UNTAP_STEP]
    }

    /**
     * On the upkeep step players can only cast instant spells or abilities
     * @return
     */
    List<CardType> castableTypes(){
        return [CardType.INSTANT]
    }
}
