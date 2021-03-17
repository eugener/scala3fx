package s3fx

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.stage.Stage

@main def startApp( args: String* ): Unit = {
    Application.launch( classOf[MyApp], args:_* )
}
class MyApp extends Application {

    override def start(stage: Stage): Unit =  {
        val label = new Label("WOW")
        val scene = new Scene(  new BorderPane(label), 1000, 800)
        stage.setScene(scene)
        stage.setTitle("Scala 3 and JavaFX")
        stage.show()
    }
    
}
