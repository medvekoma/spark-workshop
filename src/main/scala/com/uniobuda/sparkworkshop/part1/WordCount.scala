package com.uniobuda.sparkworkshop.part1

import java.io.File

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

import scala.reflect.io.Directory

object WordCount {

  def process(spark: SparkSession): Unit = {
    val userHome = System.getProperty("user.home")
    val outputFolderName = s"$userHome/wordcount"
    val outputFolder = new Directory(new File(outputFolderName))
    outputFolder.deleteRecursively()

    val result = spark.sparkContext
      .textFile(s"file:///$userHome/code/spark-workshop/nobel-laureates.csv", 4)
      .flatMap(line => line.split(Array(' ', ',', '"')))
      .map(word => (word, 1))
      .reduceByKey((a,b) => a + b)
      .sortBy(pair => pair._2, false)

    result
      .saveAsTextFile(s"file://$outputFolderName")

    result.foreach {
      case (word, count) => println(s"$word: $count")
    }
  }
}
