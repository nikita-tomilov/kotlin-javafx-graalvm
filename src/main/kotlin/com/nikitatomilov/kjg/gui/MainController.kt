package com.nikitatomilov.kjg.gui

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.nikitatomilov.kjg.api.GitHub
import com.nikitatomilov.kjg.util.MessageBoxes
import feign.Feign
import feign.Response
import feign.codec.Decoder
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import mu.KLogging
import java.lang.reflect.Type

class MainController {

  @FXML
  lateinit var cmdHelloWorld: Button

  @FXML
  fun helloWorldPressed(event: ActionEvent?) {
    val mapper = ObjectMapper().registerKotlinModule()
    val github = Feign.builder()
        .decoder(object : Decoder {
          override fun decode(r: Response, t: Type): Any {
            return mapper.readValue(r.body().toString().toByteArray(), mapper.constructType(t))
          }
        })
        .target(GitHub::class.java, "https://api.github.com")

    val contributors = github.contributors("OpenFeign", "feign")
    for ((login, contributions) in contributors) {
      logger.info { "$login ($contributions)" }
    }

    MessageBoxes.showAlert("It Works", "Hello World")
  }

  companion object : KLogging()
}