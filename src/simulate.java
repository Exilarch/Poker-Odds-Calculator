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
        for (int i = 0; i < handsStr.size(); i++) {
            String hand = handsStr.get(i);
            Hand handData = new Hand();
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
    }
}
