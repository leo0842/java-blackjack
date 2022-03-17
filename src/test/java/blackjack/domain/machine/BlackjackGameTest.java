package blackjack.domain.machine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BlackjackGameTest {

    private BlackjackGame blackjackGame;

    @BeforeEach
    void setUp() {
        blackjackGame = new BlackjackGame(List.of("eden"));
        blackjackGame.hasNextGuest();
    }

    @Test
    @DisplayName("처음 턴일 때 게스트가 있는지 확인")
    void checkExistGuest() {
        assertThat(blackjackGame.hasNextGuest()).isTrue();
    }

    @Test
    @DisplayName("카드를 준 뒤 받는 응답 객체가 해당 플레이어의 응답인지 확인")
    void checkAddCardToPlayer() {
        GameResponse gameResponse = blackjackGame.addCardToPlayer();
        assertThat(gameResponse.getName()).isEqualTo("eden");
    }

    @Test
    @DisplayName("두 명이 참가한 상태에서 결과 객체가 딜러와 참가자 1명 총 2명인지 확인")
    void checkCalculateResult() {
        Results results = blackjackGame.calculateResult();
        assertThat(results.getPlayers().size()).isEqualTo(2);
    }
}