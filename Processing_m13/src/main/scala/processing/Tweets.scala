package processing


import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col

object Tweets {

  def main(args: Array[String]): Unit = {

    val logger = Logger.getLogger("org.apache.spark")
    logger.setLevel(Level.ERROR)

    val spark = SparkSession
      .builder()
      .appName("Processing_tw")
      //.master("local[*]")
      .getOrCreate()

    val data = spark.read.json("hdfs:///user/acerrato/tweets_m3_4/events-.1551654509444")
    //val data = spark.read.json("./src/main/scala/resources/events-.1551654509444")

    val listaColumnas = List("user.id", "user.screenName","user.name","createdAt","text","user.location")
    val tweets = data.select(listaColumnas.head, listaColumnas.tail:_*)
                      .withColumn("retweet", col("text").contains("RT"))


    tweets.write
      .format("csv")
      .option("delimiter", "\u0001")
      .save("hdfs:///user/acerrato/procesados/m_3")
  }

}
