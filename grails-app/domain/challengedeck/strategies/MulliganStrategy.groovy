package challengedeck.strategies

import challengedeck.cards.Card
import challengedeck.cards.Land

class MulliganStrategy {

    public static int MIN_CARDS_STOP_MULLIGAN = 3

    Boolean needMulligan(List<Card> cards){

        if(cards.size() <= MIN_CARDS_STOP_MULLIGAN){
            return false
        }

        int amountofLands = cards.count{ it instanceof Land }
        return amountofLands < 2
    }

}
