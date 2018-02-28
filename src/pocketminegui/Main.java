package pocketminegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        primaryStage.setTitle("PocketMineGUI");
        File properties = new File("/_Developments/PocketMineGUI/server.properties");

        String serverName = null;
        if(!properties.isFile()) {
            serverName = "初回起動";
        } else {
            Properties sProperties = new Properties();
            sProperties.load(new FileInputStream(properties));
            serverName = sProperties.getProperty("motd");
        }
        primaryStage.setTitle("Server: " + serverName);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
