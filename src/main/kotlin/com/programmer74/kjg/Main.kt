package com.programmer74.kjg

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage

class Main : Application() {

  override fun start(s: Stage) {
    val fxmlFile = "/fxml/main.fxml"
    val loader = FXMLLoader()
    val root = loader.load<Parent>(javaClass.getResourceAsStream(fxmlFile))
    s.scene = Scene(root)
    s.title = "Hello World"
    s.show()
  }

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      launch(Main::class.java, *args)
    }
  }
}