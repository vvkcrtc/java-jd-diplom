package ru.netology.jddiplom.service;

import org.springframework.core.io.Resource;
import java.io.IOException;

public class FileData {
    protected Resource resource;

    public FileData(Resource resource) {
        this.resource = resource;
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

    public Resource getResource() {
        return resource;
    }
}
