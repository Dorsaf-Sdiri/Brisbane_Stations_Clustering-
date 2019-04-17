package com.citybikes.clustering

import com.citybikes.clustering.Run.logger
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.clustering.{BisectingKMeans, KMeans}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.{SaveMode, SparkSession}

class Modeling {
  val master, numberOfClusters, numberOfSeeds, inputData, outPutData, modelDir  = new prop
  val spark = SparkSession
    .builder.
    master(master.getProp("SET_MASTER"))
    .appName("Clustering for Brisbane_CityBike")
    .getOrCreate()

  // Preparing the input data
  val df = spark.read.json(inputData.getProp("INPUT_DATA"))
  val numberofClusters = numberOfClusters.getProp("NUM_CLUSTERS").toInt
  val numberofSeeds = numberOfSeeds.getProp("NUM_SEEDS").toInt
  val assembler = new VectorAssembler().setInputCols(Array("latitude", "longitude")).setOutputCol("features")
  val kmeans = new KMeans()
    .setK(numberofClusters).setSeed(numberofSeeds)
    .setFeaturesCol("features")
    .setPredictionCol("cluster")

  //Training model with KMeans
  logger.info("number of cluster(s): " + numberofClusters)
  logger.info("number of seed(s): " + numberofSeeds)
  val pipeline = new Pipeline().setStages(Array(assembler, kmeans))

  // Make predictions

  // Evaluate clustering by computing Silhouette score
  //val evaluator = new ClusteringEvaluator()
  //val silhouette = evaluator.evaluate(predictions)
  //logger.info(s"Silhouette with squared euclidean distance = $silhouette")

  // Evaluate clustering by computing Within Set Sum of Squared Errors.
  //val WSSSE = model.computeCost(df)
  //logger.info(s"Within Set Sum of Squared Errors = $WSSSE")

  // Show the result
  //logger.info("Cluster Centres: ")
  //model.clusterCenters.foreach(logger.info(_))
  val model = pipeline.fit(df)
  // Saving the model
  model.write
    .overwrite()
    .save(modelDir.getProp("MODEL_DIR"))

  logger.info( "Model has been saved in {0}"+modelDir.getProp("MODEL_DIR"))

  val clusters = model.transform(df)
  clusters.write
    .mode(SaveMode.Overwrite)
    .format("json")
    .option("header", "true")
    .option("delimiter", ";")
    .save(outPutData.getProp("OUTPUT_DIR" ))


  // Saving the dataset with labels
  clusters.drop("features")
    .repartition(1)
    .write
    .mode(SaveMode.Overwrite)
    .format("json")
    .option("header", "true")
    .option("delimiter", ";")
    .save(outPutData.getProp("OUTPUT_DIR" ))
  // Saving the dataset with labels
  logger.info( "Clustered data has been saved in "+outPutData.getProp("OUTPUT_DIR" ))
  // BisectingKMeans
  val bkm = new BisectingKMeans().setK(3).setSeed(1L).setFeaturesCol("features")
  val pipeline2 = new Pipeline().setStages(Array(assembler, bkm))
  val model2 = pipeline.fit(df)


}
