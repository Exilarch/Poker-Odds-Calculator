/**
 * Created by Andrew on 7/27/17.
 */
import java.util.ArrayList;
public class simulate {
    public static void main(String[] args) throws Exception {
        boolean isBoard = false;
        String board = "";
        ArrayList<String> handsStr = new ArrayList<>();
        ArrayList<Hand> hands = new ArrayList<>();
        for (String s : args) {
            if (s.equals("-b")) {
                isBoard = true;
            }
            if (isBoard) {
                board = s;
                isBoard = false;
            } else {
                handsStr.add(s);
            }
        }
        if (handsStr.size() < 1) {
            throw new Exception("Not enough poker hands");
        }

    }
}
