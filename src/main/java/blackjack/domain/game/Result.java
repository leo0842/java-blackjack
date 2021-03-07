package blackjack.domain.game;

import blackjack.domain.card.Cards;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;

import java.util.LinkedHashMap;
import java.util.Map;

public class Result {
    private final Map<Player, WinOrLose> gamblerResults = new LinkedHashMap<>();
    private final Cards dealerCards;

    public Result(Cards dealerCards) {
        this.dealerCards = dealerCards;
    }

    public void addGamblerResults(final Player player, WinOrLose winOrLose) {
        if (!(player instanceof Gambler)) {
            return;
        }

        gamblerResults.put(player, winOrLose);
    }

    public int countDealerWin() {
        return (int) gamblerResults.keySet().stream()
                .filter(name -> gamblerResults.get(name) == WinOrLose.LOSE)
                .count();
    }

    public int countDealerLose() {
        return (int) gamblerResults.keySet().stream()
                .filter(name -> gamblerResults.get(name) == WinOrLose.WIN)
                .count();
    }

    public int countDealerDraw() {
        return (int) gamblerResults.keySet().stream()
                .filter(name -> gamblerResults.get(name) == WinOrLose.DRAW)
                .count();
    }

    public Map<Player, WinOrLose> getGamblerMap() {
        return gamblerResults;
    }

    public Cards getDealerCards() {
        return dealerCards;
    }
}