import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

public class Lab3 {
    public static void main (String[] args){
        SparkConf conf = new SparkConf().setAppName("Lab3");
        JavaSparkContext sc = new JavaSparkContext(conf);
    }
}
