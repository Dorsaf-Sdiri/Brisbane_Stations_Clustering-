package com.citybikes.clustering
import java.util.Properties
import java.io.FileInputStream



	class prop {

		def getProp (propertyName: String) : String  = {
			val properties = new Properties()
			properties.load(new FileInputStream("Application.properties"))
			val property = properties.getProperty(propertyName)
			property.toString()

		}
	}

