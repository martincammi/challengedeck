package challengedeck

enum Trigger {

    ENTER_THE_BATTLEFIELD,
    LEAVES_THE_BATTLEFIELD,
    DAMAGE_CREATURE,
    BEGINNING_OF_COMBAT,
    BEGINNING_OF_COMBAT_NEXT_TURN,
    DECLARE_ATTACKERS,
    DECLARE_BLOCKERS,
    DECLARE_ATTACKERS_EACH,
    BEGINNING_END_STEP,
    BEGINNING_UPKEEP,
    UNTAP_STEP,
    DRAW,
    EXILE_BEGINNING_UPKEEP,
    BEGINNING_MAIN_PHASE_1,
    BEGINNING_MAIN_PHASE_2,
    BLOCKED,
    CAN_BE_BLOCKED,
    CAN_BE_TARGET,
    REMOVE_ALL_COUNTERS,
    REVEAL,
    PLAYER_DISCARD_CARD,
    CREATURE_DEALS_NON_COMBAT_DAMAGE,
    TAP,
    STATIC,
    STATIC_CREATURE_ENTERS_THE_BATTLEFIELD,
    STATIC_CREATURE_LEAVES_THE_BATTLEFIELD,
    EXILE_STATIC_CREATURE_ENTERS_THE_BATTLEFIELD,
    ASSIGN_COMBAT_DAMAGE,
    END_OF_COMBAT_DAMAGE,
    END_OF_TURN,
    RESOLVE,
    WOULD_LEAVE_THE_BATTLEFIELD,
    CHECK_SUSPEND,
    CLEANUP
}
