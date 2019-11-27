import org.apache.spark.api.java.JavaRDD;

public class LambdaLib {
    private static final CharSequence FLIGHTSTITLE = ;

    public static JavaRDD<String[]> parseFlights(JavaRDD<String> flightsTable) {
        return flightsTable.filter(s-> !s.contains(FLIGHTSTITLE))
                .;
    }
}
