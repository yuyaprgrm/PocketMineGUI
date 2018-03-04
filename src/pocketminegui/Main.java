package pocketminegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pocketminegui.io.ProcessHolder;
import pocketminegui.io.ServerHandler;

import java.io.File;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        primaryStage.setTitle("PocketMineGUI");
        File properties = new File("/_Developments/PocketMineGUI/server.properties");

        String serverName = null;

        /*
        try {
            ResourceBundle bundle = ResourceBundle.getBundle();
            serverName = bundle.getString("motd");
        } catch (MissingResourceException e) {
        }*/

        primaryStage.setTitle("Server: " + serverName);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }

    @Override
    public void stop() throws Exception{
        ServerHandler.getInstance().stop();
        ProcessHolder.getInstance().stop();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
