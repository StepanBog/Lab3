import org.apache.spark.api.java.JavaRDD;

public class LambdaLib {
    public static JavaRDD<String[]> parseFlights(JavaRDD<String> flightsTable) {
        return flightsTable.filter(s-> !s.contains(""))
    }
}
