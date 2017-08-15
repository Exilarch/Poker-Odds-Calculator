/**
 * Created by Andrew on 7/27/17.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
    public Ranking getHandValue() {
        return Rank;
    }
    public static HandValue evaluate(Hand h) {
        HandValue r;
        ArrayList<Card> cards = h.getCardList();
        Collections.sort(cards);
        int count = cards.size();
        ArrayList<Card> spadeNum = new ArrayList<Card>();
        ArrayList<Card> clubNum = new ArrayList<Card>();
        ArrayList<Card> diamondNum = new ArrayList<Card>();
        ArrayList<Card> heartNum = new ArrayList<Card>();
        for (Card c : cards) {
            if (c.getSuit() == Card.Suit.SPADE) {
                spadeNum.add(c);
            } else if (c.getSuit() == Card.Suit.CLUB) {
                clubNum.add(c);
            } else if (c.getSuit() == Card.Suit.DIAMOND) {
                diamondNum.add(c);
            } else if (c.getSuit() == Card.Suit.HEART) {
                heartNum.add(c);
            }
        }
        boolean spadeFlush = false;
        boolean clubFlush = false;
        boolean diamondFlush = false;
        boolean heartFlush = false;
        if (spadeNum.size() >= 5) {
            spadeFlush = true;
        }
        if (clubNum.size() >= 5) {
            clubFlush = true;
        }
        if (diamondNum.size() >= 5) {
            diamondFlush = true;
        }
        if (heartNum.size() >= 5) {
            heartFlush = true;
        }
        if (count > 4) {
            ArrayList<Card> flushCards = new ArrayList<>();
            if (spadeFlush) {
                flushCards = spadeNum;
            } else if (clubFlush) {
                flushCards = clubNum;
            } else if (diamondFlush) {
                flushCards = diamondNum;
            } else if (heartFlush) {
                flushCards = heartNum;
            }
            if (!flushCards.isEmpty()) {
                Value v = flushCards.get(0).getValue();
                int straightCounter = 1;
                for (int i = 1; i < flushCards.size(); i++) {
                    Value ov = flushCards.get(i).getValue();
                    int comparison = v.compareTo(ov);
                    if (comparison != 1) {
                        straightCounter = 0;
                    }
                    straightCounter++;
                    if (straightCounter == 5) {
                        r = new HandValue(Ranking.STRAIGHT_FLUSH);
                        return r;
                    }
                }
            }
        }
        if (count > 3) {
            HashMap<Integer, Integer> duplicates = new HashMap<>();
            for (int i = 0; i < count; i++) {
                Integer value = cards.get(i).getValue().getInt();
                if (duplicates.get(value) == null) {
                    duplicates.put(value, 1);
                } else {
                    duplicates.put(value, duplicates.get(value) + 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : duplicates.entrySet()) {
                if (entry.getValue() == 4) {
                    r = new HandValue(Ranking.FOUR);
                    return r;
                }

            }
        }
        if (count > 4) {
            ArrayList<Value> pair = new ArrayList<Value>();
            ArrayList<Value> trio = new ArrayList<Value>();
            for (int i = 0; i < count; i++) {
                for (int j = i + 1; j < count; j++) {
                    for (int k = j + 1; k < count; k++) {
                        if (cards.get(i).getValue().equals(cards.get(j).getValue()) && cards.get(j).getValue().equals(cards.get(k).getValue())) {
                            if (!trio.contains(cards.get(i).getValue())) {
                                trio.add(cards.get(i).getValue());
                            }
                        }
                    }
                    if (!trio.isEmpty() && cards.get(i).getValue().equals(cards.get(j).getValue()) && !pair.contains(cards.get(i).getValue()) && !trio.contains(cards.get(i).getValue())) {
                        pair.add(cards.get(i).getValue());
                    }
                }
            }
            if (!trio.isEmpty() && !pair.isEmpty()) {
                r = new HandValue(Ranking.FULL_HOUSE);
            }
        }
        if (spadeFlush || clubFlush || diamondFlush || heartFlush) {
            r = new HandValue(Ranking.FLUSH);
            return r;
        }
        if (count > 4) {
            for (int i = 0; i < count - 3; i++) {
                Value v = cards.get(i).getValue();
                int straightCounter = 0;
                for (int j = i + 1; j < count; j++) {
                    Value ov = cards.get(j).getValue();
                    int comparison = v.compareTo(ov);
                    if (comparison == 1) {
                        straightCounter++;
                        if (straightCounter == 4) {
                            r = new HandValue(Ranking.STRAIGHT);
                            return r;
                        }
                    } else if (comparison < 0) {
                        break;
                    }
                }
            }
        }
        if (count > 2) {
            HashMap<Integer, Integer> duplicates = new HashMap<>();
            for (int i = 0; i < count; i++) {
                Integer value = cards.get(i).getValue().getInt();
                if (duplicates.get(value) == null) {
                    duplicates.put(value, 1);
                } else {
                    duplicates.put(value, duplicates.get(value) + 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : duplicates.entrySet()) {
                if (entry.getValue() == 3) {
                    r = new HandValue(Ranking.THREE);
                    return r;
                }

            }
        }
        if (count > 3) {
            ArrayList<Value> twoPairs = new ArrayList<Value>();
            for (int i = 0; i < count; i++) {
                for (int j = i + 1; j < count; j++) {
                    if (cards.get(i).getValue().equals(cards.get(j).getValue()) && !twoPairs.contains(cards.get(i).getValue())) {
                        twoPairs.add(cards.get(i).getValue());
                    }
                }
            }
        }
        if (count > 1) {
            HashMap<Integer, Integer> duplicates = new HashMap<>();
            for (int i = 0; i < count; i++) {
                Integer value = cards.get(i).getValue().getInt();
                if (duplicates.get(value) == null) {
                    duplicates.put(value, 1);
                } else {
                    duplicates.put(value, duplicates.get(value) + 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : duplicates.entrySet()) {
                if (entry.getValue() == 2) {
                    r = new HandValue(Ranking.PAIR);
                    return r;
                }

            }
        }
        r = new HandValue(Ranking.HIGH_CARD);
        for (int i = 0; i < 5 && i < count; i++) {
            r.addHigh(cards.get(i).getValue());
        }
        return r;
    }

}
