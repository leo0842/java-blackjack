package controller;

import domain.card.CardFactory;
import domain.card.Cards;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    public static void run() {
        try {
            runWithoutExceptionCatch();
        } catch (Exception e) {
            OutputView.printExceptionMessage(e);
        }
    }

    private static void runWithoutExceptionCatch() {
        Players players = new Players(InputView.readPlayerNames());
        Dealer dealer = new Dealer();
        Deck deck = CardFactory.createShuffledDeck();

        doFirstDeal(players, dealer, deck);
        dealToPlayers(players, deck);
        dealToDealer(dealer, deck);

        OutputView.printFinalCardStatus(dealer, players);
        conclude(players, dealer);
    }

    private static void doFirstDeal(Players players, Dealer dealer, Deck deck) {
        dealer.receiveFirstCards(deck);
        players.receiveFirstCards(deck);
        OutputView.printFirstCardDealt(dealer, players);
    }

    private static void dealToPlayers(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            dealToPlayer(deck, player);
        }
    }

    private static void dealToPlayer(Deck deck, Player player) {
        String playerName = player.getName();
        while (player.canReceiveCard() && InputView.askWantMoreCard(playerName)) {
            player.receiveCard(deck);
            OutputView.printPlayerCards(player);
        }
    }

    private static void dealToDealer(Dealer dealer, Deck deck) {
        while (!dealer.isLargerThan(Cards.MAX_SUM_FOR_DEALER_MORE_CARD)) {
            dealer.receiveCard(deck);
            OutputView.printDealerHasReceivedMoreCard();
        }
    }

    private static void conclude(Players players, Dealer dealer) {
        OutputView.printResultMessage();
        OutputView.printDealerResult(dealer.calculateResult(players));
        for (Player player : players.getPlayers()) {
            OutputView.printPlayerResult(
                player.getName(),
                dealer.calculateResult(player).getOpponentResult()
            );
        }
    }
}