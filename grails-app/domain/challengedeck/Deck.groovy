package challengedeck

import challengedeck.cards.Card
import challengedeck.exceptions.NoCardsInLibraryException

class Deck {

    String name
    List<Card> cards = new ArrayList<>()

    Integer size(){
        return cards.size()
    }

    void shuffle(){
        Collections.shuffle(cards)
        this.cards = cards
    }

    def draw(){
        if(cards.isEmpty()){
           throw new NoCardsInLibraryException()
        }
        def firstCard = cards.first()
        cards.remove(firstCard)

        return firstCard
    }

    def addMany(Integer many, Class cardClass){

        many.times {
            cards.add(cardClass.newInstance())
        }
    }
}
