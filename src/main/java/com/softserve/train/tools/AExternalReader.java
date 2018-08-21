package com.softserve.train.tools;

import java.util.List;

public abstract class AExternalReader {
    protected final String FILE_NOT_FOUND_EXCEPTION = "File %s could not be found";
    protected final String FILE_NOT_READ_EXCEPTION = "File %s could not be read";
    protected final String FILE_NOT_CLOSE_EXCEPTION = "File %s could not be closed";
    protected final String DB_READING_ERROR = "DB Reading Error, %s";
    protected final String PATH_SEPARATOR = "/";

    protected String filename;
    protected String path;
    
    public AExternalReader(String filename) {
        this.filename = filename;
        this.path = this.getClass().getResource(PATH_SEPARATOR + filename).getPath().substring(1);
        System.out.println("***PATH = " + path);
    }

    public String getFilename() {
        return this.filename;
    }

    public String getPath() {
        return this.path;
    }
    
    public abstract List<List<String>> getAllCells();
    
    public abstract List<List<String>> getAllCells(String path);

}
