
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.broadcast.Broadcast;
import scala.Tuple1;
import scala.Tuple12;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Lab3 {
    public static void main (String[] args) {
        SparkConf conf = new SparkConf().setAppName("Lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flightsTable = sc.textFile("BG.csv");
        JavaRDD<String> airportsTable = sc.textFile("SM.csv");

        JavaRDD<String[]> parsedFlights = LambdaLib.parseFlights(flightsTable);
        JavaRDD<String[]> parsedAirports = LambdaLib.parseAirports(airportsTable);

        JavaPairRDD<Tuple2<String,String>, FlightKey> id_ID_Delay_Cancelled_Pair = LambdaLib.pair_ID_ID_Delay_Cancelled(parsedFlights);

        JavaPairRDD<String,String> id_Name_Pair = LambdaLib.pair_ID_Name(parsedAirports);
        Map<String,String> airportMap = LambdaLib.toMap(id_Name_Pair);
        final Broadcast<Map<String, String>> airportsBroadcasted =
                sc.broadcast(airportMap);
        JavaPairRDD<Tuple2<String,String>,FlightKey> reducedFlights = LambdaLib.reduce(id_ID_Delay_Cancelled_Pair);
        JavaRDD<List<String>> result = reducedFlights.map(s-> Arrays.asList(airportsBroadcasted.value().get(s._1()._1()),airportsBroadcasted.value().get(s._1()._2()),s._2().tostring()));
        result.saveAsTextFile("output9");
}
}
