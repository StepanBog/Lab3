import java.io.Serializable;

public class FlightKey implements Serializable {

    public double delay;
    public int cancelledCount = 0;
    public int count = 0;
    public int delayedCount = 0;

    public FlightKey(String s, String s1) {
        cancelledCount = Integer.parseInt(s1);
        if (cancelledCount != 0) {
            delay = Double.parseDouble(s);
            delayedCount = 1;
        }
        else {
            delay = 0.0;
            delayedCount = 0;
        }
        count = 1;

    }
    public FlightKey(int s,  int s1, int s2, int s3) {
        delay = s;
        cancelledCount = s1;
        delayedCount = s2;
        count = s3;
    }
}
