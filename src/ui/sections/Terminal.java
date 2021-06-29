package ui.sections;

import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Terminal extends BorderPane {
    private final TextArea textArea = new TextArea();

    public Terminal() {
        textArea.setEditable(false);

        HBox hBox = new HBox();
        Button exit = new Button("X");
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.SOMETIMES);
        hBox.getStyleClass().add("hbox");
        hBox.getChildren().addAll(spacer, exit);
        setTop(hBox);

        setCenter(textArea);

        exit.setOnAction(e -> {
            ((BorderPane) getParent()).setBottom(null);
        });
    }

    public void runCommand(String command, String path) throws IOException {
        Process child = Runtime.getRuntime().exec(command, null, new File(path));
        BufferedReader in = new BufferedReader(new InputStreamReader(child.getInputStream()));
        BufferedReader error = new BufferedReader(new InputStreamReader(child.getErrorStream()));

        textArea.clear();
        String line;
        while ((line = in.readLine()) != null) {
            textArea.setStyle(null);
            textArea.appendText(line + "\n");
        }
        while ((line = error.readLine()) != null) {
            textArea.setStyle("-fx-text-fill: firebrick !important;");
            textArea.appendText(line + "\n");
        }
    }

    public void addText(String text) {
        textArea.appendText(text);
    }

}
