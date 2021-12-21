package challengedeck.players

import challengedeck.Deck
import challengedeck.DualGame
import challengedeck.cards.Card
import challengedeck.cards.Player
import challengedeck.cards.Strategy
import challengedeck.strategies.AlwaysAttackPlayerStrategy
import grails.util.Pair

class DeckPlayer implements Player {

    String name
    Deck deck
    List<Card> hand = new ArrayList()
    Strategy strategy = new AlwaysAttackPlayerStrategy()

    DeckPlayer(){}

    DeckPlayer(String name = "Robert", Deck deck, Strategy strategy = null){
        this.name = name
        this.deck = deck
        if(strategy) {
            this.strategy = strategy
        }
    }

    @Override
    Boolean needMulligan() {
        strategy.needMulligan(hand)
    }

    Pair<Card, List<Card>> wantToPlaySpell(DualGame dualGame){
        return strategy.nextCardToPlay(dualGame,this)
    }
}
