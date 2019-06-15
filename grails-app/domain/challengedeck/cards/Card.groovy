package challengedeck.cards

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.abilities.Ability

abstract class Card {

    String name;
    List<CardType> cardTypes
    List<CardSubType> cardSubTypes
    List<Ability> abilities
    Boolean hasSummoningSickness
    Map<Counter, Integer> counters
    String owner
    String controller

    Card(){
        this.hasSummoningSickness = true
        this.counters = new HashMap<>()
    }

    abstract void resolve(CthulhuGame game)

    public Card(String name, def cardTypes){
        this.name = name
        this.cardTypes = cardTypes
    }

    boolean isPermanent(){
        return cardTypes.contains(CardType.CREATURE) ||
                cardTypes.contains(CardType.ARTIFACT) ||
                cardTypes.contains(CardType.ENCHANTMENT) ||
                cardTypes.contains(CardType.LAND) ||
                cardTypes.contains(CardType.PLANESWALKER)
    }

    static constraints = {
    }

    void show(){
        println name + " " + counters.toString()
    }

    String string(){
        return name + " " + counters.toString()
    }

    boolean hasCounter(Counter counter){
        return counters.containsKey(counter)
    }
}
