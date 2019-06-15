package challengedeck.cards


import challengedeck.decks.cthulhudeck.CthulhuDeck

class CreatureToken extends Creature implements Permanent {

    CreatureToken(String name, def cardTypes, def cardSubTypes, def abilities, def power, def toughness){

        this.name = name
        this.cardTypes = cardTypes
        this.cardSubTypes = cardSubTypes
        this.abilities = abilities
        this.power = power
        this.toughness = toughness
        this.owner = CthulhuDeck.CTHULHU_NAME
    }

    static constraints = {
    }
}
