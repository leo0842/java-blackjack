package model;

import java.util.List;

import static model.Bet.LOWER_BET_BOUND;

public class Player extends User {
    private final Bet bet;

    public Player(String name, Bet bet, Deck deck) {
        super(name, deck);
        this.bet = bet;
    }

    public Player(String name, List<Card> cards) {
        super(name, cards);
        this.bet = new Bet(Double.toString(LOWER_BET_BOUND));
    }

    public double getMultiplyBet(double ratio) {
        return bet.multiplyBet(ratio);
    }

    @Override
    public boolean isHitBound() {
        return cardHand.isLowerThanBlackJack();
    }
}