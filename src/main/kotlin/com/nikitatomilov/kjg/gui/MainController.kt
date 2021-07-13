package com.nikitatomilov.kjg.gui

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.nikitatomilov.kjg.api.GitHub
import com.nikitatomilov.kjg.util.MessageBoxes
import feign.Feign
import feign.jackson.JacksonDecoder
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import mu.KLogging

class MainController {

  @FXML
  lateinit var cmdHelloWorld: Button

  @FXML
  fun helloWorldPressed(event: ActionEvent?) {
    val github = Feign.builder()
        .decoder(JacksonDecoder(ObjectMapper().registerKotlinModule()))
        .target(GitHub::class.java, "https://api.github.com")

    val contributors = github.contributors("OpenFeign", "feign")
    for ((login, contributions) in contributors) {
      logger.info { "$login ($contributions)" }
    }

    MessageBoxes.showAlert("It Works", "Hello World")
  }

  companion object : KLogging()
}