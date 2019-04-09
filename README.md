
# CityBikes Clustering

Clustering of citybikes stations based on the location. 

## Build status

[![Build Status](https://travis-ci.org/maroil/citybikes-clustering.svg?branch=master)](https://travis-ci.org/maroil/citybikes-clustering)

## Requirements

* [Java 8](https://www.java.com/fr/download/faq/java8.xml)
* [Scala 2.11.12](https://www.scala-lang.org/download/2.11.12.html)
* [SBT 1.0.3](http://www.scala-sbt.org/download.html)
* [Spark 2.1.2](https://www.apache.org/dyn/closer.lua/spark/spark-2.1.2/spark-2.1.2-bin-hadoop2.7.tgz)

## Workflow

![Workflow](https://i.imgur.com/DfJzxi8.png)

[Image link](https://i.imgur.com/DfJzxi8.png)

## Configuration

Before executing the code, you have to complete the configuration file available in **./src/resources/application.properties**.

    input.data=
    output.dir=
    model.dir=

* **input.data** Path to the file Brisbane_CityBike.json
* **output.dir** The directory that will be used to store clustered data
* **model.dir** Directory that will be used to store the model generate in case you will need to reuse it

## Running without installing Spark

The project could be run locally without installing Spark. This will use an "emulated" Spark wish is included in the dependencies of the project.

To do this, you should change the content of the file **build.sbt** like following :

    name := "citybikes-clustering"

    version := "0.1"

    scalaVersion := "2.11.12"

    val sparkVersion = "2.1.2"

    libraryDependencies ++= Seq(
	    "org.apache.spark" %% "spark-core" % sparkVersion,
	    "org.apache.spark" %% "spark-sql" % sparkVersion,
	    "org.apache.spark" %% "spark-mllib" % sparkVersion,
	    "com.typesafe" % "config" % "1.2.1"
    )

What we did here, is removing the "provided" option. This will force sbt to include Spark dependencies when compiling the project. 

You can then compile files : 

    sbt clean compile
    
Then run the job :

    sbt run

## Running on Spark

To build the project, run : 

    sbt clean assembly
    
This will produce **citybikes-clustering/target/scala-2.11/citybikes-clustering-assembly-0.1.jar** which contains the compiled project.

Then you can submit the job using **spark-submit** :

    ./bin/spark-submit --class "com.citybikes.clustering.Run" \                                                                                                                                       !9342
              --master local \
              ../citybikes-clustering/target/scala-2.11/citybikes-clustering-assembly-0.1.jar

_Make sure that you put the correct path to the JAR file._

## Results

It seems to me that the most appropriate number of clusters for this use case is 5. The cluster number could be then interpreted as north, south, east, west and center.

This is a plot of stations on a map based on the result of the clustering :

![Clustered stations plot](https://i.imgur.com/ob2FnFW.png)

[Image link](https://i.imgur.com/ob2FnFW.png)
