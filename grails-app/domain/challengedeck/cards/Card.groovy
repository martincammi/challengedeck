package challengedeck.cards

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.abilities.Ability

abstract class Card {

    String name
    List<CardType> cardTypes
    List<CardSubType> cardSubTypes
    List<Ability> abilities
    Boolean hasSummoningSickness
    Map<Counter, Integer> counters
    String owner //This will indicate the Deck to which the card belongs not the player itself (design decision)
    String controller
    Card exiledBy
    Integer cost = 0

    Card(){
        this.hasSummoningSickness = true
        this.counters = new HashMap<>()
    }

    abstract void resolve(CthulhuGame game)

    Card(String name, def cardTypes){
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

    Integer getCounter(Counter counter){
        if(counters.get(counter) == null){
            return 0
        }else{
            return counters.get(counter)
        }
    }

    void addCounter(Counter counter){
        if(getCounter(counter) == null){
            counters.put(counter, 0)
        }else{
            counters.put(counter, getCounter(counter) + 1)
        }
    }

    void removeCounter(Counter counter){
        if(getCounter(counter) != null && getCounter(counter) > 0){
            counters.put(counter, getCounter(counter) - 1)
        }
    }

    void setCounter(Counter counter, Integer amount){
        if(amount > 0){
            amount.times { addCounter(counter) }
        }

        if(amount == 0){
            counters.put(counter, 0)
        }
    }

    void removeAllCounters(Counter counter){
        setCounter(counter, 0)
    }
}
