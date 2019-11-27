import jdk.jfr.FlightRecorder;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple1;
import scala.Tuple12;

public class Lab3 {
    public static void main (String[] args){
        SparkConf conf = new SparkConf().setAppName("Lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> flightsTable = sc.textFile("BG.csv");
        JavaRDD<String> airportsTable = sc.textFile("SM.csv");

        JavaRDD<String[]> parsedFlights = LambdaLib.parseFlights(flightsTable);
        JavaRDD<String[]> parsedAirports = LambdaLib.parseAirports(airportsTable);

        JavaPairRDD<Tuple12<String,String>, FlightKey> id_Flight_Pair = LambdaLib.pairI
    }
}
