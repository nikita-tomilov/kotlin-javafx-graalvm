package com.programmer74.kjg.gui

import com.fasterxml.jackson.databind.ObjectMapper
import com.programmer74.kjg.api.GitHub
import com.programmer74.kjg.util.MessageBoxes
import feign.Feign
import feign.jackson.JacksonDecoder
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

class MainController {

  @FXML
  lateinit var cmdHelloWorld: Button

  @FXML
  fun helloWorldPressed(event: ActionEvent?) {


    val github = Feign.builder()
        .decoder(JacksonDecoder(ObjectMapper().registerKotlinModule()))
        .target(GitHub::class.java, "https://api.github.com")

    // Fetch and print a list of the contributors to this library.

    // Fetch and print a list of the contributors to this library.
    val contributors = github.contributors("OpenFeign", "feign")
    for ((login, contributions) in contributors) {
      println("$login ($contributions)")
    }

    MessageBoxes.showAlert("It Works", "Hello World")
  }

}