package com.citybikes.clustering
import org.apache.spark.sql.{SparkSession,SaveMode,DataFrame, functions}
import org.apache.log4j.{Level, Logger}
/*import vegas._
import co.theasi.plotly
import vegas.render.WindowRenderer._
import vegas.sparkExt._
import scala.math._
import scala.io._
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.mllib.evaluation.MulticlassMetrics

import smile._
import smile.plot.dendrogram
import smile.graph._
import smile.math._
import smile.data._
import smile.clustering.HierarchicalClustering
import org.apache.spark.ml.evaluation.ClusteringEvaluator */


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


		val our_model = new Modeling


		/*val predictions2 = model2.transform(df3)
		val cost = model2.computeCost(df3)
		// Comparing the predictions of Kmeans and bisectingKmeans
		val adjustedRandIndex = GetRandIndex(predictions, predictions2)
		// Data Viz
    display(model, df3) Databricks

		 case class dsSchema (
													address:String,
													cluster:Int,
													latitude:Double ,
													longitude:Double,
													name:String,
													number:Int
												)

    val ds =df.select( df("address").cast("String").as("address"),df("cluster").cast("Int").as("cluster"),df("latitude").cast("Double").as("latitude"),df("longitude").cast("Double").as("longitude"),df("name").cast("String").as("name"),df("number").cast("Int").as("number")).as[dsSchema]
    ds.printSchema()
    val dfNull = ClassifiedDF.na.fill(100)
    import spark.implicits._
		val dfWithSchema = dfNull.toDF("address", "cluster", "latitude","longitude","name","number")
		dfNull.printSchema()


    val plot= Vegas("Sample Scatterplot", width=800, height=600)
  		.withDataFrame(dfNull)
		  .mark(Point)
      .encodeX("latitude", Quantitative)
      .encodeY("longitude", Quantitative)
      .encodeColor("cluster", dataType=Nominal, bin=Bin(maxbins=5.0))

    plot.show

		Plot().withScatter("latitude", "longitude")
		val clusters3 = hclust(pdist(df3), "complete")
		dendrogram(clusters3)
		val y = clusters3.partition(5)
		plot(df3, y, '.', Palette.COLORS) */

	}
}