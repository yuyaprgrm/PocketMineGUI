package pocketminegui.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

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

    public Socket socket = null;

    public void asyncConnect() throws IOException {
        new Thread(new ConnectThread()).start();
    }

    public void start() {
        try {
            asyncConnect();
        } catch (IOException e) {
            System.out.println("ソケットの開放に失敗。");
            System.out.println("port 20132のプロセスを停止してください。");
            return;
        }
    }

}

class ConnectThread implements Runnable {

    @Override
    public void run() {
        try{
            ServerSocket server = new ServerSocket();
            server.bind(new InetSocketAddress(
                    "127.0.0.1",
                    20132
            ));

            System.out.println("接続待機中: Plugin側からの接続を待機してください");
            ServerHandler.getInstance().socket = server.accept();

            System.out.println("接続完了: Plugin側から接続されました!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}