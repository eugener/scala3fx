import sbt.Keys.libraryDependencies

val scala3Version = {
    val version = "3.2.0"
    System.setProperty("scala.version", version)
    version
}
val fxVersion = "16"


val projectName = "scala3fx"
val projectVersion = "0.1.0"

val fxModules = Seq("controls")

val osName: SettingKey[String] = SettingKey[String]("osName")

osName := (System.getProperty("os.name") match {
    case name if name.startsWith("Linux") => "linux"
    case name if name.startsWith("Mac") => "mac"
    case name if name.startsWith("Windows") => "win"
    case _ => throw new Exception("Unknown platform!")
})

lazy val root = project
  .in(file("."))
  .settings(
      name := projectName,
      version := projectVersion,
      scalaVersion := scala3Version,
      libraryDependencies ++= fxModules
        .map { m => "org.openjfx" % s"javafx-$m" % fxVersion classifier osName.value },
      //    libraryDependencies +=  "com.novocode" % "junit-interface" % "0.11" % "test"

  )

//    run / fork := true
//    run / javaOptions ++= Seq(
//        "--module-path=" + fullClasspath,
//        "--add-modules=" + jfxModules.map(module => s"javafx.$module").mkString(",")
//    )



