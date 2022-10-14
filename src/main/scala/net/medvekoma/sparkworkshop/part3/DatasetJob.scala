package net.medvekoma.sparkworkshop.part3

import net.medvekoma.sparkworkshop.SparkSessionFactory
import net.medvekoma.sparkworkshop.SparkSessionFactory.RichSparkSession
import org.apache.spark.sql.Encoders

import scala.util.Using

object DatasetJob extends App {

  implicit val encoder = Encoders.product[Laureate]

  Using.resource(SparkSessionFactory.create()) { spark =>

    val df = spark.read
      .option("header", "true")
      .csv("nobel-laureates.csv")
      .as[Laureate]

    val result = df
      .filter(_.bornCountryCode == "HU")
      .sort("year")
      .collect

    for (laureate <- result) {
      println(laureate)
    }

    spark.checkUI()
  }
}
