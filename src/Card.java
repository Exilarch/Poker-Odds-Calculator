/**
 * Created by Andrew on 7/27/17.
 */
public class Card implements Comparable<Card> {
    public enum Suit {
        SPADE, CLUB, DIAMOND, HEART
    }
    private Suit suit;
    private Value value;
    public Card(Suit s, Value v) {
        this.suit = s;
        this.value = v;
    }
    public Suit getSuit() {
        return suit;
    }
    public Value getValue() {
        return value;
    }
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (!(other instanceof Card)) {
            return false;
        }
        Card ocard = (Card) other;
        return (ocard.getValue() == this.getValue() && ocard.getSuit() == this.getSuit());
    }
    public int compareTo(Card c) {
        return this.getValue().getInt() - c.getValue().getInt();
    }
}
