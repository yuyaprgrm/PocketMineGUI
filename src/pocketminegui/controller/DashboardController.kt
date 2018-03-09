package pocketminegui.controller

import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.ListView
import pocketminegui.model.ServerManagerModel

class DashboardController {

    var model: ServerManagerModel? = null

    @FXML lateinit var chatList : ListView<String>

    @FXML
    fun clickStartButton(ev: ActionEvent) {
        model?.start()
    }
}