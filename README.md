# Spark Workshop

This repository contains the Spark demo associated with an introductory Spark presentation.

## System Requirements

This workshop uses two approaches for running Spark:
* The command line interface `spark-shell`
* The embedded Spark in the Scala SBT application.

We provided a VM that has all dependencies pre-installed. 

Running the examples directly is also possible after 
[installing Spark](https://www.knowledgehut.com/blog/big-data/install-spark-on-ubuntu).

### Using the VM

Spark uses the available CPU cores for parallelization. Make sure that you have
more than 1 CPUs assigned to your VirtualBox image.

![CPU count](img/vm-cpu.png)

## Clean up VM

The virtual image distributed has the [original version](https://github.com/medvekoma/spark-workshop) of this repository checked out.

You need to either upgrade your Git remote references or just remove the folder and clone this repository.
```bash
cd ~/code

rm -rf spark-workshop/

git clone https://github.com/david-szabo-epam/spark-workshop.git
```


## Running Spark from the command line

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

Spark shell [demo on streaming](./StreamingDemo.md) is available in a different document.

## Run the Scala code

The two ways to run the code are described below.
In both cases observe the URL of the Spark UI, and open it in a browser.

### From command line

```bash
cd ~/code/spark-workshop/
sbt run
```

### From the code editor

* Start IntelliJ from the left side panel
* Run Program.scala

## Homework

Display the top 10 countries by number of Nobel laureates in Physics.
* Output columns: Country code, country name, number of laureates
* Use all 3 approaches: DataFrame, DataSet, SparkSQL
