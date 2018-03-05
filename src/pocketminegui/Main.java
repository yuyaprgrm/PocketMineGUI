package pocketminegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pocketminegui.io.ProcessHolder;
import pocketminegui.io.ServerHandler;

import java.io.File;
import java.io.FileInputStream;
import java.util.MissingResourceException;
import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("fxml/dashboard.fxml"));
        primaryStage.setTitle("PocketMineGUI");
        File properties = new File("/_Developments/PocketMineGUI/server.properties");

        String serverName = null;

        try {
            Properties propertie = new Properties();
            propertie.load(new FileInputStream(properties));
            serverName = propertie.getProperty("motd");
        } catch (MissingResourceException e) {
        }

        primaryStage.setTitle("Server: " + serverName);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setMinWidth(primaryStage.getWidth());
        primaryStage.setMinHeight(primaryStage.getHeight());


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
