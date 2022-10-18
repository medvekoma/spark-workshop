package net.medvekoma.SparkWorkshop.part4

import net.medvekoma.SparkWorkshop.SparkSessionFactory.RichSparkSession
import net.medvekoma.SparkWorkshop.SparkSessionFactory

import scala.util.Using

object SparkSQLJob extends App {

  Using.resource(SparkSessionFactory.create()) { spark =>

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

    val dataFrame = spark.sql(sqlText)

    dataFrame.printSchema()

    dataFrame.show(100)

    spark.checkUI()
  }
}
