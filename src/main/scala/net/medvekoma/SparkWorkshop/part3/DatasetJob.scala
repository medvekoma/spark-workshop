package net.medvekoma.SparkWorkshop.part3

import net.medvekoma.SparkWorkshop.SparkSessionFactory
import net.medvekoma.SparkWorkshop.SparkSessionFactory.RichSparkSession
import org.apache.spark.sql.Encoders

import scala.util.Using

object DatasetJob extends App {

  implicit val encoder = Encoders.product[Laureate]

  Using.resource(SparkSessionFactory.create()) { spark =>

    val dataset = spark.read
      .option("header", "true")
      .csv("nobel-laureates.csv")
      .as[Laureate]

    val result = dataset
      .filter(_.bornCountryCode == "HU")
      .sort("year")
      .collect

    for (laureate <- result) {
      println(laureate)
    }

    spark.checkUI()
  }
}
