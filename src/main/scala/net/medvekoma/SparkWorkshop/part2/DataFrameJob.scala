package net.medvekoma.SparkWorkshop.part2

import net.medvekoma.SparkWorkshop.SparkSessionFactory
import net.medvekoma.SparkWorkshop.SparkSessionFactory.RichSparkSession

import scala.util.Using

object DataFrameJob extends App {

  val userHome = System.getProperty("user.home")

  Using.resource(SparkSessionFactory.create()) { spark =>

    val df = spark.read
      .format("csv")
      .option("header", "true")
      .load("nobel-laureates.csv")
      .where("borncountrycode = 'HU'")
      .orderBy("year")

    df.printSchema()

//    df.cache()

    df.write
      .mode("overwrite")
      .json(s"$userHome/output/part2")

    val result = df.collect()

    for (row <- result) {
      println(row)
    }

    println(s"There are ${df.count()} laureates born in Hungary.")

    spark.checkUI()
  }
}
