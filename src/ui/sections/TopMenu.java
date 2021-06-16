package ui.sections;

import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

public class TopMenu extends HBox {

    public TopMenu() {
        Node runIcon = new ImageView(new Image("ui/assets/run.png"));

        Menu m = new Menu("File");

        MenuItem newFile = new MenuItem("New file");
        MenuItem openFile = new MenuItem("Open file");
        MenuItem saveFile = new MenuItem("Save file");
        MenuItem openFolder = new MenuItem("Open folder");
        m.getItems().add(newFile);
        m.getItems().add(openFile);
        m.getItems().add(openFolder);
        m.getItems().add(saveFile);
        MenuBar mb = new MenuBar();
        mb.getMenus().add(m);

        Button run = new Button("Run", runIcon);

        Region spacer = new Region();
        spacer.getStyleClass().add("menu-bar");
        setHgrow(spacer, Priority.SOMETIMES);

        getChildren().addAll(mb, spacer, run);

//        saveFile.setOnAction(e -> {
//        });
//
//        run.setOnAction(e -> {
//        });
//
//        openFile.setOnAction(e -> {
//        });
//
//        newFile.setOnAction(e -> {
//
//        });
    }
}
