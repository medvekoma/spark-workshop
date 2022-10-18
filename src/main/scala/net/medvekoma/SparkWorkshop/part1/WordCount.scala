package net.medvekoma.SparkWorkshop.part1

import net.medvekoma.SparkWorkshop.SparkSessionFactory
import net.medvekoma.SparkWorkshop.SparkSessionFactory.RichSparkSession

import java.io.File
import scala.reflect.io.Directory
import scala.util.Using

object WordCount extends App {

  val userHome = System.getProperty("user.home")
  val outputFolderName = s"$userHome/output/part1"
  val outputFolder = new Directory(new File(outputFolderName))

  Using.resource(SparkSessionFactory.create()) { spark =>

    val result = spark.sparkContext
      .textFile("nobel-laureates.csv", 4)
      .flatMap(line => line.split(Array(' ', ',', '"')))
      .map(word => (word, 1))
      .reduceByKey((a, b) => a + b)
      .sortBy(_._2, ascending = false)

    result.take(10).foreach {
      case (word, count) => println(s">>> $word: $count")
    }

    outputFolder.deleteRecursively()
    result
      // .coalesce(1)
      .saveAsTextFile(s"file:///$outputFolderName")

    spark.checkUI()
  }
}
