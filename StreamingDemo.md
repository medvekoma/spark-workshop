# Streaming demo

## Simple transformation

Open a new terminal and arrange them so you can see both of them.
Run Netcat in the new terminal.
```bash
nc -lk 9999
```
Enter a few short lines of text.

In the original terminal in Spark shell

```scala
// Create input stream
val input = spark.readStream
  .format("socket")
  .option("host", "localhost")
  .option("port", 9999)
  .load()

// Check that it is a streaming Dataframe
input.isStreaming

// Calculate line length
val lineLength = input.select(col("value"), length(col("value")))

// Create output stream
val query = lineLength.writeStream
  .outputMode("append")
  .format("console")
  .start()
```
Type in a few more lines after Spark finished the initial processing.
Spark will show the new line entered only.

## Aggregation

Restart Netcat then go back to Spark shell.

```scala
// Calculate word count
val wordCount = input.as[String]
	.flatMap(_.split(" "))
	.groupBy("value")
	.count()

// Create output stream
val query = wordCount.writeStream
  .outputMode("complete")
  .format("console")
  .start()
```
Type in some words in Netcat.
Spark will update the full word count state after each line.