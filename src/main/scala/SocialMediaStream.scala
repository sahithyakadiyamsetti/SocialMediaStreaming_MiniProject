import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object SocialMediaStream {
  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("SocialMediaStreaming")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._
    spark.sparkContext.setLogLevel("Warn")

    val lines = spark.readStream
    .format("socket")
    .option("host", "localhost")
    .option("port", 9999)
    .load()

    val words = lines.as[String]
      .flatMap(_.split(" "))

    val wordCounts = words.withColumnRenamed("value", "word")
      .groupBy("word")
      .count()


    val trending = wordCounts
      .withColumn("timestamp", current_timestamp())
      .orderBy(desc("count"))
      .limit(7)

    val query = trending.writeStream
      .outputMode("complete")
      .format("console")
      .option("truncate", false)
      .start()

    query.awaitTermination()
  }
}