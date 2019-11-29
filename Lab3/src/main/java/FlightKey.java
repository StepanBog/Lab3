import java.io.Serializable;

public class FlightKey implements Serializable {

    public int delay;
    public int cancelledCount = 0;
    public int count = 0;
    public int delayedCount = 0;

    public FlightKey(String s, String s1) {
        cancelledCount = Integer.parseInt(s1);
        if (cancelledCount != 0)
            delay = (int)Double.parseDouble(s);
        else
            delay = 0;

    }
    public FlightKey(int s,  int s1, int s2, int s3) {
        delay = s;
        cancelledCount = s1;
        delayedCount = s2;
        count = s3;
    }
}
