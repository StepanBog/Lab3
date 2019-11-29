import java.io.Serializable;

public class FlightKey implements Serializable {

    private final double Delay;
    public final int Cancelled;
    public final int count = 0;

    public FlightKey(String s, String s1) {
        Delay = Double.parseDouble(s);
        Cancelled = Integer.parseInt(s1);
    }
}
