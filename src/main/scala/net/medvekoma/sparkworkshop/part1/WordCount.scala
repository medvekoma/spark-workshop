package net.medvekoma.sparkworkshop.part1

import net.medvekoma.sparkworkshop.SparkSessionFactory
import net.medvekoma.sparkworkshop.SparkSessionFactory.RichSparkSession

import java.io.File
import scala.reflect.io.Directory
import scala.util.Using

object WordCount extends App {

  val userHome = System.getProperty("user.home")
  val outputFolderName = s"$userHome/wordcount"
  val outputFolder = new Directory(new File(outputFolderName))

  Using.resource(SparkSessionFactory.create()) { spark =>

    val result = spark.sparkContext
      .textFile("nobel-laureates.csv", 4)
      .flatMap(line => line.split(Array(' ', ',', '"')))
      .map(word => (word, 1))
      .reduceByKey((a, b) => a + b)
      .sortBy(_._2)

    result.foreach {
      case (word, count) => println(s">>> $word: $count")
    }

    outputFolder.deleteRecursively()
    result
      // .coalesce(1)
      .saveAsTextFile(s"file:///$outputFolderName")

    spark.checkUI()
  }
}
