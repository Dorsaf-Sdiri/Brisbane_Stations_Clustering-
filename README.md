
# Geolocation data clustering

We aim to cluster cityBikes stations based on their location (Longitude and latitude). For that, we will run Kmeans and BisectingKMeans.

## Requirements

* [Java 8]
* [Scala 2.11.8]
* [SBT 1.2.1]
* [Spark 2.1.2]


## Configuration
Configure the libraries in build.sbt and download them from maven repository.
Before executing the code, you have to complete the configuration file available in **./src/resources/application.properties**.

    input.data=
    output.dir=
    model.dir=
    num.clusters=
    num.seeds=

* **input.data** Path to the file Brisbane_CityBike.json
* **output.dir** The directory that will be used to store clustered data
* **model.dir** Directory that will be used to store the model generate in case you will need to reuse it
## Workflow
1) Preprocess the data and keep the quantitative variables
2) Run Kmean and  bisecting Kmeans
3) Compare the predictions using Rand Index
![Image link](https://Clusters.png)
## Data Visualization
Vegas library\\
Smile library\\
Plotly library\\
Wispy library\\
SwiftVis2\\
Databricks has a built-in display() command that can display DataFrames as a table and create convenient one-click plots.
## Unit testing
ScalaTest: assert evaluation\\
To make sure of the consistency of predictions, I run the model once and saved the predictions and each time the test compare the new predictions to the saved one.\\


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



