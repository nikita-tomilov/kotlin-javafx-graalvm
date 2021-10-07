package com.nikitatomilov.kjg

import com.nikitatomilov.kjg.gui.MainView
import tornadofx.App

class Main : App(MainView::class) {

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      launch(Main::class.java, *args)
    }
  }
}