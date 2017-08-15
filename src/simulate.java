/**
 * Created by Andrew on 7/27/17.
 */
import java.util.ArrayList;
import java.util.HashMap;
public class simulate {
    public static void main(String[] args) throws Exception {
        ArrayList<String> handsStr = new ArrayList<>();
        ArrayList<Hand> hands = new ArrayList<>();
        for (String s : args) {
            handsStr.add(s);
        }
        if (handsStr.size() < 1) {
            throw new Exception("Not enough cards");
        }
        HashMap<String, Card> remainingCards = new HashMap<>();
        for (int i = 1; i < 14; i++) {
            Card dummyCard = new Card(Card.Suit.SPADE, new Value(i));
            remainingCards.put(dummyCard.getValue().toString() + "S", new Card(Card.Suit.SPADE, new Value(i)));
            remainingCards.put(dummyCard.getValue().toString() + "C", new Card(Card.Suit.CLUB, new Value(i)));
            remainingCards.put(dummyCard.getValue().toString() + "D", new Card(Card.Suit.DIAMOND, new Value(i)));
            remainingCards.put(dummyCard.getValue().toString() + "H", new Card(Card.Suit.HEART, new Value(i)));
        }
        Hand handData = new Hand();
        for (int i = 0; i < handsStr.size(); i++) {
            String hand = handsStr.get(i);
            for (int j = 0; j + 1 < hand.length(); j = j + 2) {
                String value = hand.substring(j, j + 1);
                String suit = hand.substring(j + 1, j + 2);
                String c;
                    if (value.equals("A")) {
                        c = "A" + suit;
                    } else if (value.equals("J")) {
                        c = "J" + suit;
                    } else if (value.equals("Q")) {
                        c = "Q" + suit;
                    } else if (value.equals("K")) {
                        c = "K" + suit;
                    } else {
                        c = value + suit;
                    }
                    handData.addCard(remainingCards.get(c));
                    remainingCards.remove(c);

            }
        }
        System.out.println(remainingCards.size());
        double high_counter = 0;
        double pair_counter = 0;
        double twopair_counter = 0;
        double three_counter = 0;
        double straight_counter = 0;
        double flush_counter = 0;
        double fullhouse_counter = 0;
        double four_counter = 0;
        double straightflush_counter = 0;
        double total_counter = 0;
        for (Card c : remainingCards.values()) {
            if (handData.getCardList().size() == 6) {
                handData.addCard(c);
                HandValue h = HandValue.evaluate(handData);
                System.out.println(h.getHandValue());
                if (h.getHandValue() == HandValue.Ranking.HIGH_CARD) {
                    high_counter++;
                } else if (h.getHandValue() == HandValue.Ranking.PAIR) {
                    pair_counter++;
                } else if (h.getHandValue() == HandValue.Ranking.TWO_PAIR) {
                    twopair_counter++;
                } else if (h.getHandValue() == HandValue.Ranking.THREE) {
                    three_counter++;
                } else if (h.getHandValue() == HandValue.Ranking.STRAIGHT) {
                    straight_counter++;
                } else if (h.getHandValue() == HandValue.Ranking.FLUSH) {
                    flush_counter++;
                } else if (h.getHandValue() == HandValue.Ranking.FULL_HOUSE) {
                    fullhouse_counter++;
                } else if (h.getHandValue() == HandValue.Ranking.FOUR) {
                    four_counter++;
                } else if (h.getHandValue() == HandValue.Ranking.STRAIGHT_FLUSH) {
                    straightflush_counter++;
                }
                total_counter++;
                handData.removeCard(c);
            }
        }
        System.out.println("High card %: " + high_counter / total_counter);
        System.out.println("Pair %: " + pair_counter / total_counter);
        System.out.println("Two pair %: " + twopair_counter / total_counter);
        System.out.println("Three of a kind %: " + three_counter / total_counter);
        System.out.println("Straight %: " + straight_counter / total_counter);
        System.out.println("Flush %: " + flush_counter / total_counter);
        System.out.println("Full house %: " + fullhouse_counter / total_counter);
        System.out.println("Four of a kind %: " + four_counter / total_counter);
        System.out.println("Straight flush %: " + straightflush_counter / total_counter);

    }
}
