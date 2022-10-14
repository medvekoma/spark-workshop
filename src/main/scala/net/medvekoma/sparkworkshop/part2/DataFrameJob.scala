package net.medvekoma.sparkworkshop.part2

import net.medvekoma.sparkworkshop.SparkSessionFactory
import net.medvekoma.sparkworkshop.SparkSessionFactory.RichSparkSession

import scala.util.Using

object DataFrameJob extends App {

  val userHome = System.getProperty("user.home")

  Using.resource(SparkSessionFactory.create()) { spark =>

    val df = spark.read
      .format("csv")
      .option("header", "true")
      .load("nobel-laureates.csv")
      .where("borncountrycode = 'HU'")

//    df.cache()

    df.write
      .mode("overwrite")
      .json(s"$userHome/hungarians")

    val result = df.collect()

    for (row <- result) {
      println(row)
    }

    spark.checkUI()
  }
}
