package pocketminegui.io;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import pocketminegui.Controller;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerHandler {


    // For Singleton
    private static ServerHandler instance = null;

    public static ServerHandler getInstance() {
        if (instance == null) {
            instance = new ServerHandler();
        }
        return instance;
    }

    private ServerHandler(){
    }

    // Singleton block end

    Socket socket = null;
    private ConnectThread thread = null;


    ObservableList playerList = null;
    ObservableList pluginsList = null;

    private void asyncConnect() throws IOException {
        thread = new ConnectThread();
        new Thread(thread).start();
    }

    public void start(ObservableList players, ObservableList plugins) {
        playerList = players;
        pluginsList = plugins;

        try {
            asyncConnect();
        } catch (IOException e) {
            System.out.println("ソケットの開放に失敗。");
            System.out.println("port 20132のプロセスを停止してください。");
        }
    }


    public void runService() {
        UpdateInfomationThread thread = new UpdateInfomationThread();
        thread.playerList = playerList;
        thread.pluginsList = pluginsList;
        new Thread(thread).start();
    }

     public void stop() {
        if(socket == null) { // socket接続してないとき
            if(thread == null) {
                return;
            }
            try {
                connectMySelf();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { // 接続中なら
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void connectMySelf() throws IOException {
        Socket soc = new Socket();
        soc.connect(new InetSocketAddress(InetAddress.getByName("localhost"), 20132));
    }

}

class ConnectThread implements Runnable {

    private ServerSocket server = null;

    @Override
    public void run() {
        try{
            server = new ServerSocket();
            server.bind(new InetSocketAddress(
                    "127.0.0.1",
                    20132
            ));

            System.out.println("接続待機中: Plugin側からの接続を待機してください");
            Socket socket = server.accept();
            ServerHandler.getInstance().socket = socket;
            System.out.println("接続完了: Plugin側から接続されました!");

            System.out.println("メッセージ送信テスト...");
            BufferedWriter writer = (new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
            writer.write("Welcome PocketMineGUI 1.0a!!!!\n");
            writer.flush();

            ServerHandler.getInstance().runService();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class UpdateInfomationThread implements Runnable {

    ObservableList playerList;
    ObservableList pluginsList;

    @Override
    public void run() {

        ServerHandler handler = ServerHandler.getInstance();

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(handler.socket.getInputStream()));
            while (!handler.socket.isClosed()) {

//                System.out.println("HELLO");
                String json = reader.readLine();

                ObjectMapper mapper = new ObjectMapper();
                JsonNode node = mapper.readTree(json);
                String type = node.get("type").asText();

                switch (type) {
                    case "plugins":
                        Platform.runLater(() -> {
                            ArrayList<String> plugins = new ArrayList<>();

                            for (Iterator<JsonNode> elements = node.get("data").elements(); elements.hasNext();) {
                                plugins.add(elements.next().asText());
                            }
                            pluginsList.setAll(plugins);
                        });
                        break;

                    case "players":
                        Platform.runLater(() -> {
                            ArrayList<String> players = new ArrayList<>();

                            for (Iterator<JsonNode> elements = node.get("data").elements(); elements.hasNext();) {
                                players.add(elements.next().asText());
                            }
                            playerList.setAll(players);
                        });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }



    }
}
