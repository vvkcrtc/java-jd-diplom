package ru.netology.jddiplom.service;

import org.springframework.core.io.Resource;
import java.io.IOException;

public class FileData {
    protected Resource resource;
    protected String username;

    public FileData(Resource resource, String username) {
        this.resource = resource;
        this.username = username;
    }

    public FileProperty getFileProperty()  {
        FileProperty fp = null;
        try {
            fp = new FileProperty(resource.getFilename(), Math.toIntExact(resource.contentLength()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return fp;
    }

    public String getUserName() {
        return username;
    }

    public Resource getResource() {
        return resource;
    }
    public Resource getResourceByUserName(String  username) {
        if (this.username.equals(username)) {
            return resource;
        }
        else {
            return null;
        }
    }


}
