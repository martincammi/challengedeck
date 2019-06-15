package challengedeck.cards

import challengedeck.Deck

interface Player {

    List getHand();

    Deck getDeck();

    Strategy getStrategy();
}