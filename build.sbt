name := "Brisbane_Stations_Clustering-"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.1.2"

libraryDependencies ++= Seq(
	"org.apache.spark" %% "spark-core" % sparkVersion,
	"org.apache.spark" %% "spark-sql" % sparkVersion,
	"org.apache.spark" %% "spark-mllib" % sparkVersion,
	"org.scalatest" %% "scalatest" % "3.0.5" % "test",
	"org.scalatest" %% "scalatest-funsuite" % "3.0.0-SNAP13",
	"org.scalatest" %% "scalatest-matchers" % "3.0.0-SNAP13",
  "com.github.haifengl" % "smile-core" % "1.5.2",
	"com.github.haifengl" % "smile-graph" % "1.5.2",
	"com.github.haifengl" % "smile-math" % "1.5.2",
	"com.github.haifengl" % "smile-data" % "1.5.2",
  "com.github.haifengl" % "smile-plot" % "1.5.2",
	"org.vegas-viz" %% "vegas" % "0.3.6",
	"org.vegas-viz" %% "vegas-spark" % "0.3.6",
  "co.theasi" %% "plotly" % "0.2.0",
	"com.typesafe" % "config" % "1.2.1"
)
