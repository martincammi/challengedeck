package challengedeck.cards

import challengedeck.CthulhuGame

class Creature extends Card implements Permanent {

    protected Integer power
    protected Integer toughness
    Boolean shouldAttack = false
    Integer modifiedPower = 0//For cases when the power is modified for some effect
    Integer modifiedToughness = 0//For cases when the toughness is modified for some effect

    Integer getPower(){
        return power + modifiedPower
    }

    Integer getToughness(){
        return toughness + modifiedToughness
    }

    void increasePower(Integer power){
        modifiedPower += power
    }

    void increaseToughness(Integer toughness){
        modifiedToughness += toughness
    }

    void reset(){
        modifiedPower = 0
        modifiedToughness = 0
    }

    @Override
    void resolve(CthulhuGame game) {
        game.putOnBattlefield(this)
//        game.resolveStack()
    }

    static constraints = {
    }
}
