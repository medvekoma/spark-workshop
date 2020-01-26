package com.uniobuda.sparkworkshop.part2

import org.apache.spark.sql.SparkSession

object Frame {

  def process(): Unit = {
    val spark = SparkSession.builder()
      .master("local[*]")
      .appName("FrameApp")
      .getOrCreate()

    val df = spark.read
      .format("csv")
      .option("header", "true")
      .load("nobel-laureates.csv")
    val result = df
      .where("borncountrycode = 'HU'")
      .collect()

    for (row <- result) {
      println(row)
    }

    spark.close()
  }

}
