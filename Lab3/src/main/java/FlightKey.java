import java.io.Serializable;

public class FlightKey implements Serializable {

    public double delay;
    public int cancelledCount = 0;
    public int count = 0;
    public int delayedCount = 0;

    public FlightKey(String s, String s1) {
        delay = Double.parseDouble(s);
        cancelledCount = Integer.parseInt(s1);
    }
    public FlightKey(double s,  int s1, int s2, int s3) {
        delay = s;
        cancelledCount = s1;
        delayedCount = s2;
        count = s3;
    }
}
