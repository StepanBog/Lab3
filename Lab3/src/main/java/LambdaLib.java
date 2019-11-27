import org.apache.spark.api.java.JavaRDD;

import java.util.Arrays;

public class LambdaLib {
    private static final CharSequence FLIGHTSTITLE = ;
    private static final String FLIGHTSSPLIT = ",";

    public static JavaRDD<String[]> parseFlights(JavaRDD<String> flightsTable) {
        return flightsTable.filter(s-> !s.contains(FLIGHTSTITLE))
                .map(s-> Arrays.stream(s.split(FLIGHTSSPLIT)))
                .toArray(String[] :: new);
    }
}
