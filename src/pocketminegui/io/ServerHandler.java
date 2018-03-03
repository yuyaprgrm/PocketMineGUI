package pocketminegui.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;

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

    private void asyncConnect() throws IOException {
        thread = new ConnectThread();
        new Thread(thread).start();
    }

    public void start() {
        try {
            asyncConnect();
        } catch (IOException e) {
            System.out.println("ソケットの開放に失敗。");
            System.out.println("port 20132のプロセスを停止してください。");
        }
    }

     public void stop() {
        if(socket == null) { // server.accept()時なら
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}