/**
 * Created by Andrew on 7/27/17.
 */
public class Value implements Comparable<Value> {
    int value;
    public Value(int value) {
        this.value = value;
    }
    @Override
    public int compareTo(Value v) {
        return this.getInt() - v.getInt();
    }
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (!(other instanceof Value)) {
            return false;
        }
        Value ovalue = (Value) other;
        return (ovalue.getInt() == this.getInt());
    }
    public int getInt() {
        return this.value;
    }
    @Override
    public String toString() {
        String s;
        switch(this.getInt()) {
            case 10:
                s = "10";
                break;
            case 11:
                s = "J";
                break;
            case 12:
                s = "Q";
                break;
            case 13:
                s = "K";
                break;
            case 14:
                s = "A";
                break;
            default:
                s = String.valueOf(this.value);
                break;
        }
        return s;
    }
}
