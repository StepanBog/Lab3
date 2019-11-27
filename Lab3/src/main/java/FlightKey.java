import java.io.Serializable;

public class FlightKey implements Serializable {

    private final double Delay;
    private final int Cancelled;

    public FlightKey(String s, String s1) {
        Delay = Double.parseDouble(s);
        Cancelled = Integer.parseInt(s1);
    }
}
