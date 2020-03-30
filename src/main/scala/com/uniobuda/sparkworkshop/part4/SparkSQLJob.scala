package com.uniobuda.sparkworkshop.part4

import org.apache.spark.sql.SparkSession

object SparkSQLJob {

  def process(spark: SparkSession): Unit = {
    spark.read
      .format("csv")
      .option("header", "true")
      .load("nobel-laureates.csv")
      .createOrReplaceTempView("laureate")

    spark.read
      .format("csv")
      .option("header", "true")
      .load("country.csv")
      .createOrReplaceTempView("country")

    val sqlText =
      """
        |SELECT borncountrycode, first(country.name) as country, count(*) as num
        |FROM laureate LEFT JOIN country ON (laureate.borncountrycode = country.code)
        |GROUP BY borncountrycode
        |ORDER BY num DESC
        |LIMIT 20
        |""".stripMargin
    val df = spark.sql(sqlText)
    val result = df
      .collect()

    for (row <- result) {
      println(row)
    }
  }

}
