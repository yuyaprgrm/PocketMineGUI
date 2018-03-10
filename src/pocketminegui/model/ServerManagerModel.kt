package pocketminegui.model

import javafx.collections.ObservableList
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import pocketminegui.io.MapRenderer
import pocketminegui.io.ProcessHolder
import pocketminegui.io.ServerHolder
import java.io.DataOutput

class ServerManagerModel {

    var procHolder : ProcessHolder? = null
    var console : ObservableList<String>? = null
    var servHolder : ServerHolder? = null

    fun start() {
        procHolder = ProcessHolder()
        procHolder!!.output = console
        procHolder!!.start()
    }

    fun stop() {
        procHolder?.stop()
    }
}

object ServerManagerHelper {

    var model : ServerManagerModel? = null

    fun getInstance(): ServerManagerModel {
        return if(model == null) {
            model = ServerManagerModel()
            model!!
        } else {
            model!!
        }
    }
}