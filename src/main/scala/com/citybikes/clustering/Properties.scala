package com.citybikes.clustering

import com.typesafe.config._

object Properties {

	val config = ConfigFactory.load()
	val SET_MASTER = "local"
  val NUM_CLUSTERS = 5
	val NUM_SEEDS =2
	val INPUT_DATA = "/home/sdiri/Bureau/Brisbane_CityBike.json"
	val OUTPUT_DIR = "/home/sdiri/Bureau/Output"
	val MODEL_DIR = "/home/sdiri/Bureau/Output"

}
