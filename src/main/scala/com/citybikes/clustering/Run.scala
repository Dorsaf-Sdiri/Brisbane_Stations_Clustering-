package com.citybikes.clustering
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.ml.clustering.KMeans
import org.apache.spark.ml.clustering.BisectingKMeans
import org.apache.spark.sql.{SparkSession,SaveMode,DataFrame, functions}
import org.apache.log4j.{Level, Logger}
import org.apache.spark.ml.Pipeline
import vegas._
import co.theasi.plotly
import vegas.render.WindowRenderer._
import vegas.sparkExt._
//import scala.math._
//import scala.io._
//import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
//import org.apache.spark.mllib.evaluation.MulticlassMetrics

//import smile._
//import smile.plot.dendrogram
//import smile.graph._
//import smile.math._
//import smile.data._
//import smile.clustering.HierarchicalClustering
//import org.apache.spark.ml.evaluation.ClusteringEvaluator


object Run {

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


		val assembler = new VectorAssembler().setInputCols(Array("latitude", "longitude")).setOutputCol("features")
		val kmeans = new KMeans()
			.setK(numberOfClusters).setSeed(numberOfSeeds)
			.setFeaturesCol("features")
			.setPredictionCol("cluster")

		//Training model with KMeans
		logger.info("number of cluster(s): " + Properties.NUM_CLUSTERS)
		logger.info("number of seed(s): " + Properties.NUM_SEEDS)
		val pipeline = new Pipeline().setStages(Array(assembler, kmeans))
		val model = pipeline.fit(df)
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
		//val predictions2 = model2.transform(df3)
		//val cost = model2.computeCost(df3)
		// Comparing the predictions of Kmeans and bisectingKmeans
		//val adjustedRandIndex = GetRandIndex(predictions, predictions2)
		// Data Viz
    //display(model, df3) Databricks
	  val ClassifiedDF = spark.read.json(Properties.OUTPUT_CSV)
		ClassifiedDF.show()
		ClassifiedDF.printSchema()

		Vegas("Sample Scatterplot", width=800, height=600)
  		.withDataFrame(ClassifiedDF)
		  .mark(Point)
      .encodeX("latitude", Quantitative)
      .encodeY("longitude", Quantitative)
      .encodeColor("cluster", dataType=Quantitative, bin=Bin(maxbins=5.0))
      .show

		//Plot().withScatter("latitude", "longitude")
		//val clusters3 = hclust(pdist(df3), "complete")
		//dendrogram(clusters3)
		//val y = clusters3.partition(5)
		//plot(df3, y, '.', Palette.COLORS)

	}
}