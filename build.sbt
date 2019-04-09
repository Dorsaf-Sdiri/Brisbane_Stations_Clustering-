name := "Brisbane_Stations_Clustering-"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.1.2"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion,
	"org.apache.spark" %% "spark-sql" % sparkVersion,
	"org.apache.spark" %% "spark-mllib" % sparkVersion,
	"org.scalatest" %% "scalatest" % "3.0.5" % "test",
  "com.github.haifengl" % "smile-core" % "1.5.2",
	"com.github.haifengl" % "smile-graph" % "1.5.2",
	"com.github.haifengl" % "smile-math" % "1.5.2",
	"com.github.haifengl" % "smile-data" % "1.5.2",
  "com.github.haifengl" % "smile-plot" % "1.5.2",
	"com.typesafe" % "config" % "1.2.1"
)
