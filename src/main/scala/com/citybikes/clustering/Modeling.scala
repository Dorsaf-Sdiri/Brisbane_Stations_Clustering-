package com.citybikes.clustering

import com.citybikes.clustering.Run.logger
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.clustering.{BisectingKMeans, KMeans}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.{SaveMode, SparkSession}

class Modeling {
  val spark = SparkSession
    .builder.
    master(Properties.SET_MASTER)
    .appName("Clustering for Brisbane_CityBike")
    .getOrCreate()

  // Preparing the input data
  val df = spark.read.json(Properties.INPUT_DATA)
  val numberOfClusters = Properties.NUM_CLUSTERS
  val numberOfSeeds = Properties.NUM_SEEDS
  val assembler = new VectorAssembler().setInputCols(Array("latitude", "longitude")).setOutputCol("features")
  val kmeans = new KMeans()
    .setK(numberOfClusters).setSeed(numberOfSeeds)
    .setFeaturesCol("features")
    .setPredictionCol("cluster")

  //Training model with KMeans
  logger.info("number of cluster(s): " + Properties.NUM_CLUSTERS)
  logger.info("number of seed(s): " + Properties.NUM_SEEDS)
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
    .save(Properties.MODEL_DIR)

  logger.info( "Model has been saved in {0}"+Properties.MODEL_DIR)

  val clusters = model.transform(df)
  clusters.write
    .mode(SaveMode.Overwrite)
    .format("json")
    .option("header", "true")
    .option("delimiter", ";")
    .save(Properties.OUTPUT_DIR)


  // Saving the dataset with labels
  clusters.drop("features")
    .repartition(1)
    .write
    .mode(SaveMode.Overwrite)
    .format("json")
    .option("header", "true")
    .option("delimiter", ";")
    .save(Properties.OUTPUT_DIR)
  // Saving the dataset with labels
  logger.info( "Clustered data has been saved in "+Properties.OUTPUT_DIR)
  // BisectingKMeans
  val bkm = new BisectingKMeans().setK(3).setSeed(1L).setFeaturesCol("features")
  val pipeline2 = new Pipeline().setStages(Array(assembler, bkm))
  val model2 = pipeline.fit(df)


}
