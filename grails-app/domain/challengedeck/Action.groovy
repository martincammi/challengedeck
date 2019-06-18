package challengedeck

import challengedeck.abilities.Ability

abstract class Action {

    Ability ability //The ability that generate the action (the ones it belongs)
    abstract void resolve(CthulhuGame game)

}
