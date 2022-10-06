package net.medvekoma.sparkworkshop

import org.apache.spark.sql.SparkSession

object SparkFactory {

  def create(): SparkSession =
    SparkSession.builder()
      .master("local[*]")
      .appName("SparkWorkshop")
      .config("spark.sql.shuffle.partitions", "3")
      .getOrCreate()

}
