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
        this.cards = cards //Is this really necessary?
    }

    void clear(){
        cards = new ArrayList<>()
    }

    def draw(){
        if(cards.isEmpty()){
           throw new NoCardsInLibraryException()
        }
        def firstCard = cards.first()
        cards.remove(firstCard)

        return firstCard
    }

    def add(Card card){
        cards.add(card)
    }

    def add(List<Card> cards){
        this.cards.addAll(cards)
    }

    def addMany(Integer many, Class cardClass){

        Card card
        many.times {
            card = cardClass.newInstance()
            if(!card.owner){ //Owner will indicate the deck to which the card belongs, usually a card has no owner before adding it to a deck, an exception of this are Cthulhu cards, this is the why of this if.
                card.owner = this.name
            }
            cards.add(card)
        }
    }

    Card look(Integer position){
        if(position >= 0 && position < cards.size()){
            return cards.get(position)
        }
        return null
    }
}
