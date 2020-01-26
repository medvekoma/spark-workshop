package com.uniobuda.sparkworkshop.part1

import java.io.File

import org.apache.spark.SparkContext

import scala.reflect.io.Directory

object WordCount {

  def process(): Unit = {
    val homeFolderName = System.getProperty("user.home")
    val outputFolderName = s"$homeFolderName/wordcount"
    val outputFolder = new Directory(new File(outputFolderName))
    outputFolder.deleteRecursively()

    val sc = new SparkContext("local[*]", "SparkWorkshop")
    val resultRDD = sc.textFile("file:///etc/hosts", 4)
      .flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey((a,b) => a + b)

    resultRDD
      .saveAsTextFile(s"file://$outputFolderName")

    resultRDD.collect.foreach {
      case (word, count) => println(s"$word: $count")
    }
  }
}
