package net.medvekoma.sparkworkshop.part3

import net.medvekoma.sparkworkshop.SparkFactory
import org.apache.spark.sql.{Encoders, SparkSession}

import scala.util.Using

object DatasetJob extends App {

  implicit val encoder = Encoders.product[Laureate]

  Using(SparkFactory.create()) { spark =>
    import spark.implicits._

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
  }

}
