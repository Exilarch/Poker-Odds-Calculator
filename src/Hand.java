/**
 * Created by Andrew on 7/27/17.
 */
import java.util.ArrayList;
public class Hand {
    private ArrayList<Card> currCards;

    public Hand() {
        currCards = new ArrayList<Card>();
    }
    public Card getCard(int i) {
        return currCards.get(i);
    }
    public ArrayList<Card> getCardList() {
        return currCards;
    }
    public void addCard(Card c) {
        currCards.add(c);
    }
    public void removeCard(Card c) {
        currCards.remove(c);
    }
}
