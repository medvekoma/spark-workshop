name := "spark-workshop"

version := "0.1"

scalaVersion := "2.13.8"

libraryDependencies += "org.apache.spark" %% "spark-core" % "3.3.0"
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.3.0"
libraryDependencies +=  "org.apache.hadoop" % "hadoop-client" % "3.3.2"
libraryDependencies += "com.typesafe.slick" %% "slick" % "3.4.1"
libraryDependencies += "org.slf4j" % "slf4j-nop" % "2.0.3"
libraryDependencies += "com.h2database" % "h2" % "2.1.214"
