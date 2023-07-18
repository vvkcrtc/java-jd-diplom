package ru.netology.jddiplom.service;

import org.springframework.stereotype.Service;


import java.io.File;
import java.util.ArrayList;
import java.util.List;


@Service
public class CloudService {

    private List<FileData> fileData = new ArrayList<>();
    final String DEFAULT_PATH = "/home/vvk";

    public boolean addFile(String fileName) {
        File file = new File(fileName);
        if(file != null) {
            fileData.add( new FileData( new FileProperty(fileName, Math.toIntExact(file.length())), file));
            return true;
        }
        return false;
    }

    public List<FileProperty> getList() {
        List<FileProperty> fileProperty = new ArrayList<>();
        for(FileData fd : fileData) {
            fileProperty.add(fd.getFileProperty());
        }
        return fileProperty;
    }

    public FileData getFileData(String filename) {
        for(FileData fd : fileData) {
            if(filename.equals(fd.getFileProperty().getFilename())) {
                return fd;
            }
        }
        return null;
    }


}


