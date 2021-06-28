package ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import ui.sections.FileTree;
import ui.sections.Panel;
import ui.sections.Terminal;
import ui.sections.TopMenu;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane mainPane = new BorderPane();
        BorderPane EditorPane = new BorderPane();
        GridPane gp = new GridPane();

        Panel panel=new Panel();
        EditorPane.setCenter(panel);
        Terminal terminal=new Terminal();
        EditorPane.setBottom(terminal);

        ColumnConstraints fileTreeColumn=new ColumnConstraints();
        fileTreeColumn.setPercentWidth(15);
        ColumnConstraints filePanelColumn=new ColumnConstraints();
        filePanelColumn.setPercentWidth(85);

        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100);

        gp.getRowConstraints().add(rc);
        gp.getColumnConstraints().addAll(fileTreeColumn,filePanelColumn);

        gp.add(EditorPane,1,0);
        FileTree tree=new FileTree();
        gp.add(tree,0,0);

        mainPane.setTop(new TopMenu(panel,tree,terminal));
        mainPane.setCenter(gp);

        Scene scene = new Scene(mainPane);
        scene.getStylesheets().add("ui/style.css");

        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("X ide");
        primaryStage.show();
    }
}
