import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple12;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class LambdaLib {
    private static final CharSequence FLIGHTSTITLE = "\"YEAR\"" ;
    private static final String FLIGHTSSPLIT = ",";
    private static final CharSequence AIRPORTSTITLE = "Code";
    private static final String AIRPORTSSPLIT = ",(?=\")";
    private static final int ORIGIN_ID = 11;
    private static final int DECT_ID = 14;
    private static final int DELAY = 18;
    private static final int CANCELLED = 19;
    private static final int CODE = 0;
    private static final int NAME = 1;

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

    public static JavaPairRDD pair_ID_Name(JavaRDD<String[]> parsedAirports) {
        return parsedAirports.mapToPair(s-> new Tuple2<>(s[CODE],s[NAME]));
    }

    public static Map<String, String> toMap(JavaPairRDD<String, String> id_name_pair) {
        return id_name_pair.collectAsMap();
    }

    public static JavaPairRDD<Tuple2<String, String>,FlightKey> reduce(JavaPairRDD<Tuple2<String, String>,FlightKey> id_id_delay_cancelled_pair) {
        return id_id_delay_cancelled_pair.reduceByKey((FlightKey key1,FlightKey key2) ->( new FlightKey(Math.max(key1.delay,key2.delay),(key1.cancelledCount + key2.cancelledCount),(key1.delayedCount + key2.delayedCount),(key1.count + key2.count))));
    }

    public static JavaPairRDD<Tuple2<String,String>,String> enrichFlights(JavaPairRDD<Tuple2<String, String>, FlightKey> reducedFlights, Broadcast<Map<String, String>> airportsBroadcasted) {
        return reducedFlights.mapToPair(s-> airportsBroadcasted.value().get()
    }
}
