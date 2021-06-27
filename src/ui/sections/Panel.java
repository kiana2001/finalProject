package ui.sections;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;


public class Panel extends TabPane {

    public Panel(){
        EditorTab t=new EditorTab("Temp",this);
        getTabs().add(t);
    }

}
