# Spark Workshop

This repository contains the Spark demo associated with an introductory Spark presentation.

## System Requirements

This workshop uses two approaches for running Spark:
* The command line interface `spark-shell`
* The embedded Spark in the Scala SBT application.

## Running Spark from the command line

We provided a VM that has all dependencies pre-installed for spark shell.

### Using the VM

Spark uses the available CPU cores for parallelization. Make sure that you have
more than 1 CPUs assigned to your VirtualBox image.

![CPU count](img/vm-cpu.png)

Running the examples directly (without VM) is also possible after installing Spark.

### Running the samples

Open a terminal window and start the spark console:

```bash
# Go to the source folder
cd ~/code/spark-workshop/
# Start Spark Shell
spark-shell
```

Find the address of the Spark context Web UI in the output lines 
and open it in a browser (It should be something like http://10.0.2.15:4040).

Open the [Spark shell demo](./SparkShellDemo.md) documentation.

## Run the Scala code

* Start IntelliJ
* File menu > New > Project from Version control...
* Provide the url from github
* Set Directory to where you want to download the files on your loval machine
* Run Program.scala

Note the URL of the Spark UI, and open it in a browser.

## Homework

Display the top 10 countries by number of Nobel laureates in Physics.
* Output columns: Country code, country name, number of laureates
* Use all 3 approaches: DataFrame, DataSet, SparkSQL
