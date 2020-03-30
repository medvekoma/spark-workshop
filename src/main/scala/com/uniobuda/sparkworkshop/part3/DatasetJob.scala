package com.uniobuda.sparkworkshop.part3

import org.apache.spark.sql.{Encoders, SparkSession}

object DatasetJob {

  def process(spark: SparkSession): Unit = {
    implicit val encoder = Encoders.product[Laureate]

    import spark.implicits._

    val df = spark.read
      .option("header", "true")
      .csv("nobel-laureates.csv")
      .as[Laureate]
    val result = df
      .filter(_.bornCountryCode == "HU")
      .sort('year)
      .collect

    for (laureate <- result) {
      println(laureate)
    }
  }

}
