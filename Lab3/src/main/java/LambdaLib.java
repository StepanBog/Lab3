import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import scala.Tuple12;
import scala.Tuple2;

import java.util.Arrays;

public class LambdaLib {
    private static final CharSequence FLIGHTSTITLE = "\"YEAR\"" ;
    private static final String FLIGHTSSPLIT = ",";
    private static final CharSequence AIRPORTSTITLE = "Code";
    private static final String AIRPORTSSPLIT = ",(?=\")";
    private static final int ORIGIN_ID = 11;
    private static final int DECT_ID = 14;
    private static final int DELAY = 11;
    private static final int CANCELLED = 19;

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

    public static JavaPairRDD<Tuple2<String,String>,FlightKey> pair_ID_ID_Delay_Cancelled(JavaRDD<String[]> parsedFlights) {
        return parsedFlights.mapToPair(s-> new Tuple2<>(new Tuple2<>(s[ORIGIN_ID],s[DECT_ID]),new FlightKey(s[DELAY],s[CANCELLED])));
    }
}
