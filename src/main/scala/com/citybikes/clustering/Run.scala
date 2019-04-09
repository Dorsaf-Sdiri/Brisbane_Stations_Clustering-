package com.citybikes.clustering
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.clustering.BisectingKMeans
import smile._
import smile.plot._

//import org.apache.spark.ml.evaluation.ClusteringEvaluator
import org.apache.spark.sql.{SparkSession,SaveMode}
import org.apache.log4j.{Level, Logger}


object main {

	//Initialize logging
	val logger: Logger = Logger.getLogger("My.Example.Code.Rules")
	Logger.getLogger("org.apache.spark").setLevel(Level.WARN)
	Logger.getLogger("org.apache.spark.storage.BlockManager").setLevel(Level.ERROR)
	logger.setLevel(Level.INFO)

	def main(args: Array[String]): Unit = {
		//Creating a spark session
		val spark = SparkSession
			.builder.
			master(Properties.SET_MASTER)
			.appName("Clustering for Brisbane_CityBike")
			.getOrCreate()

		//Set number of cluster(s) and seed(s)
		val numberOfClusters = Properties.NUM_CLUSTERS
		val numberOfSeeds = Properties.NUM_SEEDS


		// Preparing the input data
		val df = spark.read.json(Properties.INPUT_DATA)
		val df2 = df.select("latitude", "longitude")

		val assembler = new VectorAssembler().setInputCols(Array("latitude", "longitude")).setOutputCol("features")
		val df3 = assembler.transform(df2).select("features")

		//Training model with KMeans
		logger.info("number of cluster(s): " + Properties.NUM_CLUSTERS)
		logger.info("number of seed(s): " + Properties.NUM_SEEDS)
		val kmeans = new KMeans().setK(numberOfClusters).setSeed(numberOfSeeds)
		val model = kmeans.fit(df3)

		// Make predictions
		val predictions = model.transform(df3)
		logger.info("Predictions: ")
		predictions.collect().foreach(logger.info(_))

		// Evaluate clustering by computing Silhouette score
		//val evaluator = new ClusteringEvaluator()
		//val silhouette = evaluator.evaluate(predictions)
		//logger.info(s"Silhouette with squared euclidean distance = $silhouette")

		// Evaluate clustering by computing Within Set Sum of Squared Errors.
	  val WSSSE = model.computeCost(df3)
	  logger.info(s"Within Set Sum of Squared Errors = $WSSSE")

		// Show the result
		logger.info("Cluster Centres: ")
		model.clusterCenters.foreach(logger.info(_))

		// Saving the model
		model.write
			.overwrite()
			.save(Properties.MODEL_DIR)

		logger.info( "Model has been saved in {0}"+Properties.MODEL_DIR)

		val clusters = model.transform(df)

		// Saving the dataset with labels
		clusters.drop("features")
			.repartition(1)
			.write
			.mode(SaveMode.Overwrite)
			.format("com.databricks.spark.csv")
			.option("header", "true")
			.option("delimiter", ";")
			.save(Properties.OUTPUT_DIR)
		// Saving the dataset with labels
		logger.info( "Clustered data has been saved in "+Properties.OUTPUT_DIR)
		// BisectingKMeans
		val bkm = new BisectingKMeans().setK(3).setSeed(1L).setFeaturesCol("features")
		val model2 = bkm.fit(df3)
		val cost = model2.computeCost(df3)
		// Data Viz
		def hclust(proximity: Array[Array[Double]], linkage: String): HierarchicalClustering
		def proximity[T](data: Array[T], dist: Distance[T], half: Boolean = true): Array[Array[Double]]
		def distance(data: Array[Array[Double]], half: Boolean = true): Array[Array[Double]]
		val clusters = hclust(pdist(df3), "complete")
		dendrogram(clusters)
		val y = clusters.partition(5)
		plot(x, y, '.', Palette.COLORS)

	}
}