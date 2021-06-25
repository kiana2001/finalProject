package ui.sections;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import org.fxmisc.flowless.VirtualizedScrollPane;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class EditorTab extends Tab {
    private File file=null;
    public EditorTab(String name, TabPane p){
        super(name);

        setOnCloseRequest(e->{
            if(p.getTabs().size() == 1) {
                EditorTab t = new EditorTab("Temp",p);
                t.setContent(new VirtualizedScrollPane<>(new Editor()));
                p.getTabs().add(t);
            }
        });
        setContent(new VirtualizedScrollPane<>(new Editor()));
    }
    public EditorTab(String name, TabPane p,String text){
        this(name,p);
        setContent(new VirtualizedScrollPane<>(new Editor(text)));
    }
    public EditorTab(String name, TabPane p,FileView f) throws IOException {
        this(name,p);
        this.file=f;
        FileInputStream fis = new FileInputStream(f);
        byte[] data = new byte[(int) f.length()];
        fis.read(data);
        String str = new String(data, StandardCharsets.UTF_8);
        setContent(new VirtualizedScrollPane<>(new Editor(str)));
        fis.close();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
