package blackjack.domain.card;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    public void setUp() {
        deck = new Deck();
    }
    @Test
    @DisplayName("덱 객체 생성 확인")
    public void createDeck() {
        assertThat(deck).isInstanceOf(Deck.class);
    }

    @Test
    @DisplayName("deck cards에 card 더하는 로직 확인")
    public void checkAddCard() {
        deck.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        Deck comparedDeck = new Deck();
        comparedDeck.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        assertThat(deck).isEqualTo(comparedDeck);
    }

    @Test
    @DisplayName("deck card 2개일 때 점수 합산")
    public void checkSumDeckCardsPoint() {
        deck.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        deck.addCard(new Card(Suit.SPADE, Rank.TWO));
        int sumPoint = deck.sumPoints();
        assertThat(sumPoint).isEqualTo(10);
    }

    @Test
    @DisplayName("deck card 3개일 때 점수 합산")
    public void checkSumDeckThreeCardsPoint() {
        deck.addCard(new Card(Suit.SPADE, Rank.EIGHT));
        deck.addCard(new Card(Suit.SPADE, Rank.TWO));
        deck.addCard(new Card(Suit.SPADE, Rank.JACK));
        int sumPoint = deck.sumPoints();
        assertThat(sumPoint).isEqualTo(20);
    }

    @Test
    @DisplayName("ace 4개 일때 점수 확인")
    public void checkPointsForFourAces() {
        deck.addCard(new Card(Suit.SPADE, Rank.ACE));
        deck.addCard(new Card(Suit.CLUB, Rank.ACE));
        deck.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        deck.addCard(new Card(Suit.HEART, Rank.ACE));
        int sumPoint = deck.sumPoints();
        assertThat(sumPoint).isEqualTo(14);
    }

    @Test
    @DisplayName("ace 3개 일때 13점 점수 확인")
    public void checkPointsForThreeAces() {
        deck.addCard(new Card(Suit.SPADE, Rank.ACE));
        deck.addCard(new Card(Suit.CLUB, Rank.ACE));
        deck.addCard(new Card(Suit.DIAMOND, Rank.ACE));
        deck.addCard(new Card(Suit.DIAMOND, Rank.TWO));
        int sumPoint = deck.sumPoints();
        assertThat(sumPoint).isEqualTo(15);
    }

    @Test
    @DisplayName("ace 2개 일때 12점 점수 확인")
    public void checkPointsForTwoAces() {
        deck.addCard(new Card(Suit.SPADE, Rank.ACE));
        deck.addCard(new Card(Suit.CLUB, Rank.ACE));
        deck.addCard(new Card(Suit.DIAMOND, Rank.NINE));
        int sumPoint = deck.sumPoints();
        assertThat(sumPoint).isEqualTo(21);
    }

    @Test
    @DisplayName("ace 2개 일때 2점 점수 확인")
    public void checkPointsForTwoAcesOverLimit() {
        deck.addCard(new Card(Suit.SPADE, Rank.ACE));
        deck.addCard(new Card(Suit.CLUB, Rank.ACE));
        deck.addCard(new Card(Suit.DIAMOND, Rank.JACK));
        int sumPoint = deck.sumPoints();
        assertThat(sumPoint).isEqualTo(12);
    }
}
