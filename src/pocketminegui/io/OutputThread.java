package pocketminegui.io;


import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OutputThread implements Runnable {

    private final ObservableList<String> output;
    private final Process process;

    public OutputThread(ObservableList<String> output, Process process) {
        this.output = output;
        this.process = process;
    }
    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {

                final String text = line;
                Platform.runLater(
                        () -> output.addAll(text)
                );
                if (! process.isAlive()) {
                    return;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
