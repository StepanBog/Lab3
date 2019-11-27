import org.apache.spark.api.java.JavaRDD;

import java.util.Arrays;

public class LambdaLib {
    private static final CharSequence FLIGHTSTITLE = "\"YEAR\"" ;
    private static final String FLIGHTSSPLIT = ",";
    private static final CharSequence AIRPORTSTITLE = "Code";
    private static final String AIRPORTSSPLIT = ;

    public static JavaRDD<String[]> parseFlights(JavaRDD<String> flightsTable) {
        return flightsTable.filter(s-> !s.contains(FLIGHTSTITLE))
                    .map(s-> Arrays.stream(s.split(FLIGHTSSPLIT))
                        .toArray(String[] :: new));
    }

    public static JavaRDD<String[]> parseAirports(JavaRDD<String> airportsTable) {
        return airportsTable.filter(s-> !s.contains(AIRPORTSTITLE))
                    .map(s-> Arrays.stream(s.split(AIRPORTSSPLIT))
                        .toArray(String[]::new));
    }
}
