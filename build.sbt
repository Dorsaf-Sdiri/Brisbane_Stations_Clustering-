name := "Brisbane_Stations_Clustering-"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.1.2"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion ,
	"org.apache.spark" %% "spark-sql" % sparkVersion ,
	"org.apache.spark" %% "spark-mllib" % sparkVersion 
	
)