name := """play-zero-training"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.13.5"

libraryDependencies ++= Seq(
  guice,
  jdbc,
  evolutions,
  "javax.xml.bind" % "jaxb-api" % "2.3.1",
  "javax.activation" % "activation" % "1.1.1",
  "org.glassfish.jaxb" % "jaxb-runtime" % "2.3.2",
  "mysql" % "mysql-connector-java" % "5.1.41",
)
