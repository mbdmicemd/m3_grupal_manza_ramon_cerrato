name := "Processing_m13"

version := "0.1"

scalaVersion := "2.11.10"

val sparkVersion = "2.4.0"

import sbt._


libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion,
  "org.apache.spark" %% "spark-sql" % sparkVersion,
  "org.apache.spark" %% "spark-mllib" % sparkVersion,
  "org.apache.spark" %% "spark-streaming" % sparkVersion,
  "org.apache.spark" %% "spark-hive" % sparkVersion

)

ThisBuild / assemblyJarName in assembly := s"processing_m3_4.jar"
assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @_*) => MergeStrategy.discard
  case x => MergeStrategy.first  
}

