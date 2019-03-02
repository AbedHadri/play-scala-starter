name := "play-scala-starter-example"

version := "1.0.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.7"

scalacOptions := Seq(
  "-deprecation", // Emit warning and location for usages of deprecated APIs.
  "-encoding", "utf-8", // Specify character encoding used by source files.
  "-language:existentials",// Existential types (besides wildcard types) can be written and inferred
  "-language:higherKinds", // Allow higher-kinded types 
  "-language:implicitConversions", // Allow definition of implicit functions called views
  "-unchecked",// Enable additional warnings where generated code depends on assumptions.
  "-Xfatal-warnings",
  "-Ywarn-dead-code", 
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard",
  "-Ywarn-unused-import"
)

libraryDependencies ++= {
  val metricsV = "4.0.3"
  val prometheusSimpleclientV = "0.5.0"
  val scalaLoggingV = "3.9.0"
  val kinesisClientV = "1.8.10"
  val playSlickV = "4.0.0"
  val scalaGuiceV = "4.2.1"
  val scalaUriV = "0.4.16"

  Seq(
    guice,
    "net.codingwell" %% "scala-guice" % scalaGuiceV,
      "nl.grons" %% "metrics4-scala" % metricsV,
    "io.prometheus" % "simpleclient_hotspot" % prometheusSimpleclientV,
    "io.prometheus" % "simpleclient_dropwizard" % prometheusSimpleclientV,
    "io.prometheus" % "simpleclient_common" % prometheusSimpleclientV,
    "com.typesafe.play" %% "play-slick" %  playSlickV,
    "com.typesafe.play" %% "play-slick-evolutions" % playSlickV,
    "com.netaporter" %% "scala-uri" % scalaUriV,
    "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingV,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
  )

}

// play will try to import assets to routes which is not used , causing build fails (unused warns are checked above)
play.sbt.routes.RoutesKeys.routesImport := Seq.empty

coverageMinimum := 15
coverageFailOnMinimum := false // temporarily

sbtassembly.AssemblyKeys.assemblyJarName in assembly := s"${name.value}-${version.value}.jar"