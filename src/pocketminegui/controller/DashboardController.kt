package pocketminegui.controller

import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.control.ListView
import javafx.scene.layout.VBox
import javafx.stage.Stage
import pocketminegui.Main
import pocketminegui.model.ServerManagerModel

class DashboardController : VBox() {

    var model: ServerManagerModel? = null
    var stage: Stage? = null

    @FXML lateinit var chatList : ListView<String>

    @FXML
    fun clickStartButton(ev: ActionEvent) {
        chatList.items = FXCollections.observableArrayList()
        model?.console = chatList.items
        model?.start()
    }

    @FXML fun moveToConsoleView(e : ActionEvent) {
        val loader = FXMLLoader(javaClass.getResource("/pocketminegui/fxml/consoleView.fxml"))
        val root = loader.load<Parent>()
        val controller : ConsoleController = loader.getController()
        val stage = Stage()
        stage.title = "PocketMineGUI"
        controller.parentController = this
        stage.scene = Scene(root)
        stage.show()
        this.stage!!.hide()
    }
}