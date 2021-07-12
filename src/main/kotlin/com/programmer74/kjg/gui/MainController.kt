package com.programmer74.kjg.gui

import com.programmer74.kjg.util.MessageBoxes
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button

class MainController {

  @FXML
  lateinit var cmdHelloWorld: Button

  @FXML
  fun helloWorldPressed(event: ActionEvent?) {
    MessageBoxes.showAlert("It Works", "Hello World")
  }

}