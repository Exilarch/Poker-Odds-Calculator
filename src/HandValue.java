/**
 * Created by Andrew on 7/27/17.
 */
import java.util.ArrayList;
import java.util.Collections;
public class HandValue {
    public enum Ranking {
        HIGH_CARD, PAIR, TWO_PAIR, THREE, STRAIGHT, FLUSH, FULL_HOUSE, FOUR, STRAIGHT_FLUSH
    }
    private Ranking Rank;
    private ArrayList<Value> highValues;
    public HandValue(Ranking r) {
        Rank = r;
        highValues = new ArrayList<Value>();
    }

    public ArrayList<Value> getHighValues() {
        return highValues;
    }
    public void addHigh(Value v) {
        highValues.add(v);
    }
    public static HandValue evaluate(Card... Cards) {
        HandValue r;
        ArrayList<Card> cards = new ArrayList<Card>();
        for (Card c : Cards) {
            cards.add(c);
        }
        Collections.sort(cards);
        int count = cards.size();
        r = new HandValue(Ranking.HIGH_CARD);
        for (int i = 0; i < 5 && i < count; i++) {
            r.addHigh(cards.get(i).getValue());
        }
        return null;
    }

}
