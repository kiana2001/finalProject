package ui.sections;

import java.io.File;

public class FileView extends File {

    public FileView(String pathname) {
        super(pathname);
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
