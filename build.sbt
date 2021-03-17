import sbt.Keys.libraryDependencies

val scala3Version = "3.0.0-RC1"

val jfxModules = Seq("controls")

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
      name := "scala3fx",
      version := "0.1.0",

      scalaVersion := scala3Version,

      libraryDependencies ++= jfxModules
        .map { m => "org.openjfx" % s"javafx-$m" % "16" classifier osName.value }

      //    libraryDependencies +=  "com.novocode" % "junit-interface" % "0.11" % "test"

  )

javaOptions in run ++= Seq(
    //    "--module-path", (javaFxPath.value / ("javafx-sdk-" + jfx_sdk_version) / "lib").toString,
    "--add-modules=" + jfxModules.map(m => s"javafx.$m").mkString(",")
)  


