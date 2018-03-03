package pocketminegui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pocketminegui.io.ProcessHolder;
import pocketminegui.io.ServerHandler;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        primaryStage.setTitle("PocketMineGUI");
        File properties = new File("/_Developments/PocketMineGUI/server.properties");

        String serverName = null;


        primaryStage.setTitle("Server: " + serverName);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        ServerHandler.getInstance().start();

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
