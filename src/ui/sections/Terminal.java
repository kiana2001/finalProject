package ui.sections;

import javafx.scene.control.TextArea;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal extends TextArea {
    public Terminal() {
        getStyleClass().add("black-card");
        setEditable(false);
    }

    public void runCommand(String command, String path) throws IOException {
        Process child = Runtime.getRuntime().exec(command,null,new File(path));
        BufferedReader in = new BufferedReader(new InputStreamReader(child.getInputStream()));
        BufferedReader error = new BufferedReader(new InputStreamReader(child.getErrorStream()));

        String line;
        while((line =in.readLine())!=null){
            this.appendText(line+"\n");
        }
        while((line =error.readLine())!=null){
            this.setStyle("-fx-text-fill: firebrick !important;");
            this.appendText(line+"\n");
        }
    }
}
