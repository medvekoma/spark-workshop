package com.uniobuda.sparkworkshop.part3

import org.apache.spark.sql.{Encoders, SparkSession}

object FrameSet {

  def process(): Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("FrameApp")
      .getOrCreate()

    implicit val encoder = Encoders.product[Laureate]

    val df = spark.read
      .option("header", "true")
      .csv("nobel-laureates.csv")
      .as[Laureate]
    val result = df
      .filter(_.bornCountryCode == "HU")
      .collect()

    for (laureate <- result) {
      println(laureate)
    }

    spark.close()
  }

}
