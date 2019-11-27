public class FlightKey implements Sete{

    private final double Delay;
    private final int Cancelled;

    public FlightKey(String s, String s1) {
        Delay = Double.parseDouble(s);
        Cancelled = Integer.parseInt(s1);
    }
}
