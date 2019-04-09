name := "citybikes-clustering"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.1.2"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion,
	"org.apache.spark" %% "spark-sql" % sparkVersion,
	"org.apache.spark" %% "spark-mllib" % sparkVersion,
	"org.clustering4ever" % "clustering4ever_2.11" % "0.9.4",
	"com.irvingc.spark" %% "dbscan" % "0.1.0",
	"com.typesafe" % "config" % "1.2.1"
)
resolvers += Resolver.bintrayRepo("clustering4ever", "C4E")
resolvers += "bintray/irvingc" at "http://dl.bintray.com/irvingc/maven"