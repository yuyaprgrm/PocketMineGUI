package pocketminegui.controller

import javafx.fxml.FXML
import javafx.scene.control.ListView
import javafx.scene.control.TextField

class ConsoleController() {

    @FXML lateinit var console : ListView<String>
    @FXML lateinit var commandField : TextField
    
}