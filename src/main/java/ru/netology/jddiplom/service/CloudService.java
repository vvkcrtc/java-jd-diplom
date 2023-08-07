package ru.netology.jddiplom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



@Service
public class CloudService {
    @Autowired
    private FileSystemStorageService storageService;
/*
    public CloudService(FileSystemStorageService storageService) {
        this.storageService = storageService;
    }

 */


    private List<FileData> fileData = new ArrayList<>();

    protected String getNameFromParts(String[] src) {
        String result = "";
        for(int i = 0; i < src.length; i++) {
            if(i == 0) {
                result += src[i];
            }   else {
                result += "." + src[i];
            }
        }
        return result;
    }
    public String getNewFileName(String filename) {

        String result = null;

        String[] parts = filename.split("\\.");

        String name = parts[0];
        int beginIndex = name.indexOf("(");
        int endIndex = name.indexOf(")");

        if(beginIndex != -1 && endIndex != -1) {
            String numStr = name.substring(beginIndex+1,endIndex);
            try {
                int num = Integer.parseInt(numStr)+1;
                parts[0] = name.substring(0,beginIndex+1)+num+')';
                result = getNameFromParts(parts);
            } catch (NumberFormatException nfe) {
                System.out.println("NumberFormatException: " + nfe.getMessage());
            }
        } else {
            parts[0] += "(1)";
            result = getNameFromParts(parts);

        }

        return result;
    }

    public boolean addFile(String filename, String username) throws FileNotFoundException {
        Resource resource = storageService.loadAsResource(filename);
        if(resource != null && findFileData(resource.getFilename()) == null) {
            fileData.add( new FileData(resource, username));
            return true;
        }
        return false;
    }

    public boolean renameFile(String oldFileName) throws IOException {
        Resource resource = getResource(oldFileName);

        if(resource != null ) {
            File file = resource.getFile();
            String newFileName = getNewFileName(oldFileName);
            String newFileNameFull = file.getPath().replace(oldFileName,newFileName);

            if(file.renameTo(new File(newFileNameFull))) {
                for(FileData fd : fileData) {
                    if (oldFileName.equals(fd.getResource().getFilename())) {
                        Resource newResource = storageService.loadAsResource(newFileName);
                        if(newResource != null) {
                            fd.setResource(newResource);
                        } else {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public List<FileProperty> getList(String username)  {
        List<FileProperty> fps = new ArrayList<>();
        for(FileData fd : fileData) {
            if(username.equals(fd.getUserName())) {
                fps.add(fd.getFileProperty());
            }
        }
        return fps;
    }

    public FileData findFileData(String filename) {
        for(FileData fd : fileData) {
            if(filename.equals(fd.getFileProperty().getFilename())) {
                return fd;
            }
        }
        return null;

    }

    public Resource getResource(String filename) {
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


