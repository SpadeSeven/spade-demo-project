package com.spade.zhang;

import com.google.common.base.Splitter;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

public class WordCount {

  public static void main(String[] args) {

    if (args.length != 2) {
      System.out.println("args error");
      System.exit(1);
    }

    SparkConf conf = new SparkConf().setAppName("word count");
    conf.set("spark.hadoop.validateOutputSpecs", "false");
    JavaSparkContext context = new JavaSparkContext(conf);

    JavaRDD<String> input = context.textFile(args[0]);

    JavaRDD<String> words = input.flatMap(x -> Splitter.on(" ").splitToList(x).iterator());

    JavaPairRDD<String, Integer> pairRDD = words.mapToPair(x -> Tuple2.apply(x, 1));

    JavaPairRDD<String, Integer> counts = pairRDD.reduceByKey((c1, c2) -> c1 + c2);
    counts.saveAsTextFile(args[1]);

    context.stop();
  }
}
