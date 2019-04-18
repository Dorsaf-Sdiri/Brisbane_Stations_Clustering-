package com.citybikes.clustering
import java.io.FileInputStream
import java.util.Properties




	class prop {

		def getProp (propertyName: String) : String  = {
			val properties = new Properties()
			properties.load(new FileInputStream("./src/main/resources/Application.properties"))
			val property = properties.getProperty(propertyName)
			property.toString()

		}
	}

