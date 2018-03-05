package pocketminegui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pocketminegui.io.ProcessHolder;
import pocketminegui.io.ServerHandler;

import java.io.IOException;

public class Controller {

    @FXML public Button bootButton;
    @FXML public ListView console;
    @FXML public TextField commandField;
    @FXML public ListView playerList;
    @FXML public ListView pluginsList;

    public void bootButtonClick(ActionEvent ev) {
        ObservableList<String> players = FXCollections.observableArrayList();
        ObservableList<String> plugins = FXCollections.observableArrayList();
        ObservableList<String> outputs = FXCollections.observableArrayList();

        pluginsList.setItems(plugins);
        playerList.setItems(players);
        console.setItems(outputs);

        try {
            ProcessHolder ph = ProcessHolder.getInstance();
            ph.setOutput(outputs);
            ph.start();
            ServerHandler.getInstance().start(players, plugins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void commandEnterButtonClick(ActionEvent ev) {
        ProcessHolder ph = ProcessHolder.getInstance();
        ph.command(commandField.getText() + "\n");
        commandField.setText(null);
    }


}
