package net.medvekoma.SparkWorkshop

import org.apache.spark.sql.SparkSession

import scala.io.StdIn.readLine

object SparkSessionFactory {

  def create(): SparkSession =
    SparkSession.builder()
      .master("local[*]")
      .appName("SparkWorkshop")
      .config("spark.sql.shuffle.partitions", "3")
      .getOrCreate()

  implicit class RichSparkSession(spark: SparkSession) {
    def checkUI(): Unit = {
      spark.sparkContext.uiWebUrl.map { url =>
        println(s"Check Spark UI at $url")
        println("Press ENTER to quit (twice, if needed).")
        readLine()
      }
    }
  }
}
