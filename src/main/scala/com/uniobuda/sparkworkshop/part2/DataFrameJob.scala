package com.uniobuda.sparkworkshop.part2

import org.apache.spark.sql.SparkSession

object DataFrameJob {

  def process(spark: SparkSession): Unit = {
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
  }

}
