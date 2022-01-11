package challengedeck.strategies

import challengedeck.CardSubType
import challengedeck.CardType
import challengedeck.CthulhuGame
import challengedeck.DualGame
import challengedeck.cards.Card
import challengedeck.cards.Creature
import challengedeck.cards.Land
import challengedeck.cards.Player
import challengedeck.cards.Strategy
import grails.util.Pair

class AlwaysAttackCreaturesStrategy implements Strategy {

    static constraints = {
    }

    @Override
    void onAttack(CthulhuGame game) {
        game.battlefield.findAll{ c -> c instanceof Creature && game.isActivePlayer(c.owner)}.each { Creature c -> c.shouldAttack = true}
    }

    @Override
    Boolean needMulligan(List<Card> cards) {
        return new MulliganStrategy().needMulligan(cards)
    }

    /**
     * Returns the next card to be played and taps the mana necessary to do it
     * @param dualGame
     * @param player
     * @param stepState
     * @return
     */
    Pair<Card, List<Card>> nextCardToPlay(DualGame dualGame, Player player) {

        List<Card> cardsToPlay = player.getHand().findAll { !it.cardTypes.intersect(dualGame.stepState.castableTypes()).isEmpty() && (!it instanceof Land || !dualGame.hasDropLand()) }
        Integer minCost = cardsToPlay.min { it.cost }?.cost
        List<Card> cardsWithMinCost = cardsToPlay.findAll { minCost.equals(it.cost) }
        Card cardToPlay = cardsWithMinCost.find { canBePlayed(it, dualGame, player) }

        if(cardToPlay){
            List<Card> landsToTap = landsToTap(cardToPlay, dualGame, player)
            return new Pair(cardToPlay, landsToTap)
        }

        return null
    }

    private Boolean canBePlayed(Card card, DualGame dualGame, Player player){

        List<Card> landsToTap = dualGame.getCardsFromPlayer(player, CardType.LAND)

        Boolean enoughPlains = card.getManaCost().count("W") <= landsToTap.count{ it.cardSubTypes.contains(CardSubType.PLAINS)}
        Boolean enoughMountains = card.getManaCost().count("R") <= landsToTap.count{ it.cardSubTypes.contains(CardSubType.MOUNTAIN)}
        Boolean enoughIslands = card.getManaCost().count("U") <= landsToTap.count{ it.cardSubTypes.contains(CardSubType.ISLAND)}
        Boolean enoughSwamps = card.getManaCost().count("B") <= landsToTap.count{ it.cardSubTypes.contains(CardSubType.SWAMP)}
        Boolean enoughForests = card.getManaCost().count("G") <= landsToTap.count{ it.cardSubTypes.contains(CardSubType.FOREST)}

        Boolean amountIndifferentColor = landsToTap.size() >= card.cost

        return enoughPlains && enoughMountains && enoughIslands && enoughSwamps && enoughForests && amountIndifferentColor
    }

    private List<Card> landsToTap(Card card, DualGame dualGame, Player player){

        Card foundCard
        List<Card> landsToTap = new ArrayList<>()
        List<Card> landsAvailable = dualGame.getCardsFromPlayer(player, CardType.LAND)

        landsToTap.addAll(landsAvailable.findAll { it.cardSubTypes.contains(CardSubType.PLAINS) }.take(card.getManaCost().count("W")))
        landsToTap.addAll(landsAvailable.findAll { it.cardSubTypes.contains(CardSubType.MOUNTAIN) }.take(card.getManaCost().count("R")))
        landsToTap.addAll(landsAvailable.findAll { it.cardSubTypes.contains(CardSubType.ISLAND) }.take(card.getManaCost().count("U")))
        landsToTap.addAll(landsAvailable.findAll { it.cardSubTypes.contains(CardSubType.SWAMP) }.take(card.getManaCost().count("B")))
        landsToTap.addAll(landsAvailable.findAll { it.cardSubTypes.contains(CardSubType.FOREST) }.take(card.getManaCost().count("G")))

        //2UGB
        (card.manaCost as String[]).each { manaSymbol ->

            if("W".equals(manaSymbol)){
                foundCard = landsAvailable.find { it.cardSubTypes.contains(CardSubType.PLAINS)}
            }

            if("R".equals(manaSymbol)){
                foundCard = landsAvailable.find { it.cardSubTypes.contains(CardSubType.MOUNTAIN)}
            }

            if("U".equals(manaSymbol)){
                foundCard = landsAvailable.find { it.cardSubTypes.contains(CardSubType.ISLAND)}
            }

            if("B".equals(manaSymbol)){
                foundCard = landsAvailable.find { it.cardSubTypes.contains(CardSubType.SWAMP)}
            }

            if("G".equals(manaSymbol)){
                foundCard = landsAvailable.find { it.cardSubTypes.contains(CardSubType.FOREST)}
            }

            if(isNumber(manaSymbol)){
                Integer number = Integer.valueOf(manaSymbol)
                List<Card> foundCards = landsAvailable.take(number)
                landsToTap.addAll(foundCards)
                landsAvailable.removeAll(foundCards)
            }

            if(foundCard){
                landsAvailable.remove(foundCard)
                landsToTap.add(foundCard)
            }
        }

        return landsToTap
    }

    private isNumber(String manaSymbol){
        return ( "1".equals(manaSymbol) || "2".equals(manaSymbol) || "3".equals(manaSymbol) || "4".equals(manaSymbol) ||
                 "5".equals(manaSymbol) || "6".equals(manaSymbol) || "7".equals(manaSymbol) || "8".equals(manaSymbol) ||
                 "9".equals(manaSymbol))
    }
}
