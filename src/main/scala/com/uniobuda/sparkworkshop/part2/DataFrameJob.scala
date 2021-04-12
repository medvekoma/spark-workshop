package com.uniobuda.sparkworkshop.part2

import org.apache.spark.sql.SparkSession

object DataFrameJob {

  def process(spark: SparkSession): Unit = {
    val userHome = System.getProperty("user.home")

    val df = spark.read
      .format("csv")
      .option("header", "true")
      .load("nobel-laureates.csv")
      .where("borncountrycode = 'HU'")

//    df.cache()

    df.write.mode("overwrite").json(s"$userHome/hungarians")

    val result = df.collect()

    for (row <- result) {
      println(row)
    }
  }

}
