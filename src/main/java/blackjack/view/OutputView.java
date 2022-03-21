package blackjack.view;

import blackjack.domain.machine.GameResponse;
import blackjack.domain.machine.MatchResults;
import blackjack.domain.participant.Player;
import java.util.List;

public class OutputView {

    private static final String COMMA_DELIMITER = ", ";
    private static final String DISTRIBUTE_TWO_CARDS_MESSAGE = "에게 2장의 카드를 각각 나누었습니다.";
    private static final String CARD_OUTPUT_FORMAT = "%s 카드: %s";
    private static final String NEW_LINE = "\n";
    private static final String SCORE_OUTPUT_FORMAT = " - 결과: %d";
    private static final String DEALER_GIVEN_ONE_MORE_CARD_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String DEALER_GIVEN_NO_MORE_CARD_MESSAGE = "딜러는 17이상이라 카드를 더 받지 않습니다.";
    private static final String DEALER_NAME = "딜러";

    public static void announceStartGame(List<String> playerNames) {
        System.out.println(String.join(COMMA_DELIMITER, playerNames) + DISTRIBUTE_TWO_CARDS_MESSAGE);
    }

    public static void announcePresentCards(GameResponse gameResponse) {
        printFirstStartCards(gameResponse);
    }

    public static void announcePresentCards(List<GameResponse> gameResponses) {
        for (GameResponse gameResponse : gameResponses) {
            printFirstStartCards(gameResponse);
        }
    }

    public static void announceDealerGetMoreCard() {
        System.out.println(DEALER_GIVEN_ONE_MORE_CARD_MESSAGE);
    }

    public static void announceDealerStopMoreCard() {
        System.out.println(DEALER_GIVEN_NO_MORE_CARD_MESSAGE);
    }

    public static void announceResultCards(List<GameResponse> gameResponses) {
        for (GameResponse gameResponse : gameResponses) {
            String playerName = gameResponse.getName();
            String cardOutputFormat = toCardOutputFormat(gameResponse);
            int score = gameResponse.getScore();
            System.out.printf(CARD_OUTPUT_FORMAT + SCORE_OUTPUT_FORMAT + NEW_LINE, playerName, cardOutputFormat,
                    score);
        }
    }

    public static void announceResultWinner(MatchResults matchResults) {
        for (Player player : matchResults.getPlayers()) {
            double profit = matchResults.getProfit(player);
            System.out.printf("%s: %.1f\n", player.getName(), profit);
        }
    }

    private static String toCardOutputFormat(GameResponse gameResponse) {
        return String.join(COMMA_DELIMITER, gameResponse.getDeck());
    }

    private static void printFirstStartCards(GameResponse gameResponse) {
        String playerName = gameResponse.getName();
        if (playerName.equals(DEALER_NAME)) {
            String cardOutputFormat = hideOneCard(gameResponse);
            System.out.printf(CARD_OUTPUT_FORMAT + NEW_LINE, playerName, cardOutputFormat);
            return;
        }
        String cardOutputFormat = toCardOutputFormat(gameResponse);
        System.out.printf(CARD_OUTPUT_FORMAT + NEW_LINE, playerName, cardOutputFormat);
    }

    private static String hideOneCard(GameResponse gameResponse) {
        return gameResponse.getDeck().get(0);
    }
}
