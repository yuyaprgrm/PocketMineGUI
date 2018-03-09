package pocketminegui.thread

import javafx.application.Platform
import pocketminegui.io.ProcessHolder
import java.io.BufferedReader
import java.io.InputStreamReader

class ConsoleThread(val procHolder: ProcessHolder) : Runnable {

    val reader : BufferedReader

    init {
        val process = procHolder.process
        reader = BufferedReader(InputStreamReader(process!!.inputStream))
    }

    override fun run() {
        while(true) {
            var text = reader.readLine()

            if(text.isNullOrEmpty()) {
                break
            }

            Platform.runLater({
                procHolder.output?.addAll(text)
            })
        }
    }

}