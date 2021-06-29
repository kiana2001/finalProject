package ui.sections;


import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FileTree extends TreeView<FileView> {
    private final List<String> files = new ArrayList<>();
    private final TreeItem<FileView> rootItem;

    public FileTree(Panel panel) {

        rootItem = new TreeItem<>(new FileView("Files"), new ImageView(new Image("ui/assets/folder.png")));
        rootItem.setExpanded(true);
        setRoot(rootItem);
        setCellFactory(p -> new TextFieldTreeCellImpl(panel));
    }

    public void addItem(FileView file) {
        TreeItem<FileView> item = new TreeItem<>(file, new ImageView(new Image(file.isFile() ? "ui/assets/file.png" : "ui/assets/folder.png")));
        files.add(file.getPath());
        rootItem.getChildren().add(item);
    }

    public List<String> getFiles() {
        return files;
    }

    private static final class TextFieldTreeCellImpl extends TreeCell<FileView> {

        private final ContextMenu addMenu = new ContextMenu();
        private final Panel p;

        public TextFieldTreeCellImpl(Panel p) {
            this.p = p;
            MenuItem addMenuItem = new MenuItem("Add Employee");
            addMenu.getItems().add(addMenuItem);
            addMenuItem.setOnAction(t -> {
                TreeItem<FileView> newEmployee = new TreeItem<>(new FileView("Employee"));
                getTreeItem().getChildren().add(newEmployee);
            });
        }


        @Override
        public void updateItem(FileView item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {

                setText(getString());
                setGraphic(getTreeItem().getGraphic());
                if (!item.isFile())
                    setContextMenu(addMenu);

                setOnMouseClicked(e -> {
                    if (e.getButton().equals(MouseButton.PRIMARY)) {
                        if (e.getClickCount() == 2) {
                            boolean find = false;
                            if (item.isFile()) {
                                for (var t : p.getTabs()) {
                                    if (((EditorTab) t).getFile() != null) {
                                        var path1 = ((EditorTab) t).getFile().getPath();
                                        var path2 = item.getPath();
                                        if (path1.equals(path2)) {
                                            p.getSelectionModel().select(t);
                                            find = true;
                                            break;
                                        }
                                    }
                                }
                                if (!find) {
                                    try {
                                        EditorTab t = new EditorTab(item.getName(), p, item);
                                        p.getTabs().add(t);
                                        p.getSelectionModel().select(t);
                                    } catch (IOException ex) {
                                        ex.printStackTrace();
                                    }

                                }
                            }
                        }
                    }

                });
            }
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }
}
