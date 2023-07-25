package ru.netology.jddiplom.service;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CloudService {

    private List<FileData> fileData = new ArrayList<>();


    public boolean addFile(Resource resource) {
        if(resource != null && findFileData(resource.getFilename()) == null) {
            fileData.add( new FileData(resource));
            return true;
        }
        return false;
    }

    public List<FileProperty> getList()  {
        List<FileProperty> fileProperty = new ArrayList<>();
        for(FileData fd : fileData) {
            fileProperty.add(fd.getFileProperty());
        }
        return fileProperty;
    }

    public FileData findFileData(String filename) {
        for(FileData fd : fileData) {
            if(filename.equals(fd.getFileProperty().getFilename())) {
                return fd;
            }
        }
        return null;

    }

    public Resource getFileData(String filename) {
        FileData fd = findFileData(filename);
        if(fd != null) {
            return fd.getResource();
        }
        return null;
    }

    public boolean deleteFile(String filename) {
        FileData fd = findFileData(filename);
        if(fd != null) {
            fileData.remove(fd);
            return true;
        }
        return false;
    }
    public void printCloud() {
        System.out.println("Files in cloud:");
        for(FileData fd : fileData) {
            System.out.println(fd.getFileProperty().getFilename());
        }

    }


}


