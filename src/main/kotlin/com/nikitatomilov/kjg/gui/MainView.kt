package com.nikitatomilov.kjg.gui

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.nikitatomilov.kjg.api.GitHub
import feign.Feign
import feign.jackson.JacksonDecoder
import javafx.event.ActionEvent
import javafx.scene.control.Button
import javafx.scene.layout.AnchorPane
import javafx.scene.web.WebView
import mu.KLogging
import tornadofx.*

class MainView : View() {

  override val root: AnchorPane by fxml("/fxml/main.fxml")

  private val cmdHelloWorld: Button by fxid()

  fun helloWorldPressed(event: ActionEvent?) {
    val github = Feign.builder()
        .decoder(JacksonDecoder(ObjectMapper().registerKotlinModule()))
        .target(GitHub::class.java, "https://api.github.com")

    val contributors = github.contributors("OpenFeign", "feign")
    for ((login, contributions) in contributors) {
      logger.info { "$login ($contributions)" }
    }

    logger.info { cmdHelloWorld.text }

    //MessageBoxes.showAlert("It Works", "Hello World")
    find<HelloWorld>().openWindow()
  }

  companion object : KLogging()
}

class HelloWorld : View() {

  private var wv: WebView by singleAssign()

  override val root = hbox {
    label("Hello world")
    wv = webview()
    button("kek") {
      action {
        logger.info { "kek in progress" }
        wv.engine.load("https://google.ru/")
      }
    }
  }

  companion object : KLogging()
}