package blackjack.domain.machine;

import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MatchResults {

    private final Map<Name, Double> results;

    public MatchResults(Player dealer) {
        this.results = new LinkedHashMap<>();
        this.results.put(dealer.getName(), (double) 0);
    }

    public void addResult(Player guest, Player dealer, double profit) {
        putGuestProfit(guest, profit);
        updateDealerProfit(dealer, profit);
    }

    public Set<Name> getKeys() {
        return results.keySet();
    }

    public Double getProfit(Name playerName) {
        return results.get(playerName);
    }

    private void putGuestProfit(Player guest, double profit) {
        results.put(guest.getName(), profit);
    }

    private void updateDealerProfit(Player dealer, double profit) {
        results.put(dealer.getName(), calculateDealerProfit(dealer, profit));
    }

    private double calculateDealerProfit(Player dealer, double profit) {
        return results.get(dealer.getName()) + profit * -1;
    }
}
