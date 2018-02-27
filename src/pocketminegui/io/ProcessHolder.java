package pocketminegui.io;

import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

// start.cmdを持つ
public class ProcessHolder {

    // Start Singleton
    public static ProcessHolder instance = null;
    private ObservableList<String> output;

    public static ProcessHolder getInstrance() {
        if(instance == null) {
            instance = new ProcessHolder();
        }
        return instance;
    }

    // End Singleton Block


    private Process process = null;

    private ProcessHolder() {
    }

    public void start() throws IOException {
        process = new ProcessBuilder("/_Server/PocketMine-MP/bin/php/php.exe", "/_Server/PocketMine-MP/src/pocketmine/PocketMine.php").start();
        new Thread(new OutputThread(this.output, process)).start();

    }

    public void command(String cmd) {
        if (process == null || !process.isAlive()) {
            return;
        }

        try {
            BufferedWriter writer = new BufferedWriter((new OutputStreamWriter(process.getOutputStream())));
            writer.write(cmd);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOutput(ObservableList<String> output) {
        this.output = output;
    }
}
