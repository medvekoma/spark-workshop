# Spark Workshop

This repository contains the source code of a Spark workshop.
Follow the instructions to try the samples by yourself.

This workshop uses two approaches for running Spark:
* The command line interface `spark-shell`
* The embedded Spark in the Scala SBT application.

## Using spark shell

Open a terminal window at the root of this repository,
and start the spark console:

```bash
# Start Spark Shell
spark-shell
```

Find the address of the Spark context Web UI in the output lines 
and open it in a browser (It should be something like http://10.0.2.15:4040).

**IMPORTANT:**
When pasting multiple lines to the spark shell,
type `:paste` to enter paste mode.

```scala
// Create a regular list on the Driver
val list = (0 to 99).toList

// Distribute it to the workers
val rdd = sc.parallelize(list)

// Check Partition size
rdd.partitions.size

// Filter RDD
val evens = rdd.filter(n => n % 2 == 0)

// Check UI - nothing happened

// Collect
evens.collect

// Check UI again - DAG Visualization
```
## Using the IDE

* Load the project in your favourite Scala IDE
* Run the 4 apps in the [SparkWorkshop](src/main/scala/net/medvekoma/SparkWorkshop) folder
* Optionally, you can run the [quizzes](/src/main/scala/net/medvekoma/quizzes) as well

Note the URL of the Spark UI, and open it in a browser.

## Homework

Display the top 10 countries by number of Nobel laureates in Physics.
* Output columns: Country code, country name, number of laureates
* Use all 3 approaches: DataFrame, DataSet, SparkSQL
