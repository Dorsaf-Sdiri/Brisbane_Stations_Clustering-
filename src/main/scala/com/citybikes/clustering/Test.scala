/*package com.citybikes.clustering
import org.apache.spark.sql.SparkSession
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers._

class Temp extends FunSuite with ShouldMatchers {
	val spark = SparkSession
		.builder.
		master(Properties.SET_MASTER)
		.appName("Clustering for Brisbane_CityBike")
		.getOrCreate()
	val this_model = new Modeling
	val ClassifiedDF = spark.read.json(Properties.OUTPUT_JSON)
  val ref= ClassifiedDF.select("cluster")
	test("Same predictions each time")  { this_model.clusters should equal (ref) }}
	*/

