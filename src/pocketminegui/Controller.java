package pocketminegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pocketminegui.io.ProcessHolder;

import java.io.IOException;

public class Controller {
    @FXML public Button bootButton;
    @FXML public ListView console;
    @FXML public TextField commandField;

    public void bootButtonClick(ActionEvent ev) {
        ObservableList<String> outputs = FXCollections.observableArrayList();
        console.setItems(outputs);
        try {
            ProcessHolder ph = ProcessHolder.getInstrance();
            ph.setOutput(outputs);
            ph.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void commandEnterButtonClick(ActionEvent ev) {
        ProcessHolder ph = ProcessHolder.getInstrance();
        ph.command(commandField.getText() + "\n");
    }
}
