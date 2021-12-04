name               := "specialization-benchmark"
version            := "0.1.0-SNAPSHOT"
scalaVersion       := "2.12.15"
crossScalaVersions := List("2.12.15", "2.13.7")
organization       := "com.pomadchin"
scalacOptions ++= Seq(
  "-deprecation",
  "-unchecked",
  "-language:implicitConversions",
  "-language:reflectiveCalls",
  "-language:higherKinds",
  "-language:postfixOps",
  "-language:existentials",
  "-feature"
)

fork := true

enablePlugins(JmhPlugin)
enablePlugins(GTBenchmarkPlugin)

jmhIterations   := Some(5)
jmhTimeUnit     := None // Each benchmark should determing the appropriate time unit.
jmhExtraOptions := Some("-jvmArgsAppend -Xmx8G -prof jfr")
