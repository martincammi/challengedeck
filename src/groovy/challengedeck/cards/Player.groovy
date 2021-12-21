package challengedeck.cards

import challengedeck.Deck
import challengedeck.DualGame
import grails.util.Pair

interface Player {

    String getName();

    List<Card> getHand();

    Deck getDeck();

    Strategy getStrategy();

    Boolean needMulligan();

    Pair<Card, List<Card>> wantToPlaySpell(DualGame dualGame);
}