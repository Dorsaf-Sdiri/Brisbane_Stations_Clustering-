package com.citybikes.clustering

import com.typesafe.config._

object Properties {

	val config = ConfigFactory.load()
	val SET_MASTER = "local"
  val NUM_CLUSTERS = 5
	val NUM_SEEDS =1L
	val INPUT_DATA = "Brisbane_Stations_Clustering-/Brisbane_CityBike.json"
	val OUTPUT_DIR = "Brisbane_Stations_Clustering-/Output"
	val MODEL_DIR = "Brisbane_Stations_Clustering-/Output"
  val OUTPUT_JSON= "Brisbane_Stations_Clustering-/ClassifiedDF.json"
	}
