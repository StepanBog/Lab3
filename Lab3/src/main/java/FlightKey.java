import java.io.Serializable;

public class FlightKey implements Serializable {

    public double delay;
    public int cancelledCount = 0;
    public int count = 0;
    public int delayedCount = 0;

    public FlightKey(String s, String s1) {

        if (s1.equals("0.00")) {
            cancelledCount = 0;
            if (s.equals(""))
                delay = 0;
            else {
                delay = Double.parseDouble(s);
                delayedCount = 1;
            }
        }
        else {
            delay = 0;
            cancelledCount = 1;
        }
        count = 1;

    }
    public FlightKey(double s,  int s1, int s2, int s3) {
        delay = s;
        cancelledCount = s1;
        delayedCount = s2;
        count = s3;
    }

    public String tostring() {
        return Double.toString(delay) + ", " + Double.toString((double)delayedCount/count * 100) + ", " + Double.toString((double)cancelledCount/count * 100);
    }
}
