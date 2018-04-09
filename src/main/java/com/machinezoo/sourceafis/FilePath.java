package com.machinezoo.sourceafis;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;

public class FilePath {




    private File file;
    private String name;

    public FilePath(String name) {
        this.name = name;
    }

    public Path getFilePath(){
        createFile();
        return file.toPath();
    }

    /**
     * This is the method to check and create a file if the input file name is correct.
     *
     * @return null if can not find the file in the resources, otherwise return the file obj
     */

    private File createFile() {
        try {
            URL fileUrl = getClass().getClassLoader().getResource(name);
            if (fileUrl == null) {
                return null;
            }
            file = new File(fileUrl.toURI());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }
}
