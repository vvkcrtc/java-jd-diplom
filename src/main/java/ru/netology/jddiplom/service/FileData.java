package ru.netology.jddiplom.service;

import java.io.File;

public class FileData {
    protected FileProperty fileProperty;
    protected File file;

    public FileData(FileProperty fileProperty, File file) {
        this.fileProperty = fileProperty;
        this.file = file;
    }

    public FileProperty getFileProperty() {
        return fileProperty;
    }

    public File getFile() {
        return file;
    }
}
