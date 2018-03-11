package pocketminegui.controller

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.ListView
import javafx.scene.control.TextField
import javafx.scene.layout.VBox

class ConsoleController: VBox() {

    var parentController : DashboardController? = null

    @FXML lateinit var console : ListView<String>
    @FXML lateinit var commandField : TextField

    
}