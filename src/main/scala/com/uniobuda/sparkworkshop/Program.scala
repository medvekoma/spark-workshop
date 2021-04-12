package com.uniobuda.sparkworkshop

import com.uniobuda.sparkworkshop.part1.WordCount
import com.uniobuda.sparkworkshop.part2.DataFrameJob
import com.uniobuda.sparkworkshop.part3.DatasetJob
import com.uniobuda.sparkworkshop.part4.SparkSQLJob
import org.apache.spark.sql.SparkSession

import scala.io.StdIn

object Program extends App {

  val spark = SparkSession.builder()
    .master("local[*]")
    .appName("FrameApp")
    .config("spark.sql.shuffle.partitions", "3")
    .getOrCreate()

  var choice = Char.MinValue
  do {
    choice = getChoice
    executeChoice(choice)
  } while (choice != '0')

  spark.close()

  private def getChoice: Char = {
    println(
      """
        |Please enter your choice:
        | 1 - Spark Core (WordCount)
        | 2 - DataFrame
        | 3 - DataSet
        | 4 - Spark SQL
        | 0 - exit
        |""".stripMargin)
    StdIn.readChar()
  }

  private def executeChoice(choice: Char): Unit =
    choice match {
      case '1' => WordCount.process(spark)
      case '2' => DataFrameJob.process(spark)
      case '3' => DatasetJob.process(spark)
      case '4' => SparkSQLJob.process(spark)
      case '0' => println("Bye!")
      case _ => println("ERROR: Unknown input")
    }
}
