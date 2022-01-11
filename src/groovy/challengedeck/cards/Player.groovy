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

    /**
     * The player has intention to play a land or to cast an spell
     * @param dualGame
     * @return
     */
    Pair<Card, List<Card>> wantToPlaySpell(DualGame dualGame);
}