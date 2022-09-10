package s3fx

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Label
import javafx.scene.layout.BorderPane
import javafx.stage.Stage
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.Timeline
import javafx.beans.property.IntegerProperty
import javafx.beans.property.SimpleIntegerProperty
import javafx.event.ActionEvent
import javafx.scene.paint.Color
import javafx.scene.text.{Font, Text}
import javafx.util.Duration
import org.w3c.dom.events.MouseEvent

@main def startApp( args: String* ): Unit = {
    Application.launch( classOf[MyApp], args:_* )
}


class MyApp extends Application {

    // this is set during SBT build
    val version: String = System.getProperty("scala.version")

    val title = s"Scala $version\nwith\nJavaFX"

    override def start(stage: Stage): Unit =  {

        val text = new Text()
        text.setStyle(
          "-fx-font-size: 1in;" +
          "-fx-fill: white;" +
          "-fx-text-alignment: center;" +
          "-fx-effect: dropshadow( three-pass-box , rgba(0,0,0,.3) , 3 , 0.12 , 3 , 3 );"
        )

        val scene = new Scene(  new BorderPane(text), 1000, 800)
        scene.setFill(Color.web("E45C77"))
        stage.setScene(scene)
        stage.setTitle(title.replace('\n', ' '))
        stage.show()


        val i: IntegerProperty = new SimpleIntegerProperty(0)
        val timeline: Timeline = new Timeline
        val keyFrame: KeyFrame = new KeyFrame(Duration.seconds(.1), _ =>
            if (i.get > title.length) {
                timeline.stop()
            }
            else {
                text.setText(title.substring(0, i.get))
                i.set(i.get + 1)
            }
        )
        timeline.getKeyFrames.add(keyFrame)
        timeline.setCycleCount(Animation.INDEFINITE)
        timeline.play()

    }
    
}
