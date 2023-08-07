package ru.netology.jddiplom;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.netology.jddiplom.configuration.StorageProperties;
import ru.netology.jddiplom.service.CloudService;
import ru.netology.jddiplom.service.FileSystemStorageService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.core.io.buffer.DataBufferUtils.write;
@EnableConfigurationProperties(StorageProperties.class)
public class CloudServiceTest {
    private CloudService cloudService;
    File file;
    Path testFilePath;
    String testFilePathStr;
    //private final StorageProperties properties;
    /*
    private final Path rootLocation;
    public CloudServiceTest(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
    }
*/
    //@BeforeEach
    void setup() throws IOException {

        cloudService = new CloudService();
        //Path rootLocation =  Paths.get(properties.getLocation());
        //testFilePathStr = rootLocation.toString();
        file = new File(testFilePathStr+"\\CloudServiceTest.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("Test data");
        writer.close();

                //testFilePath = Files.createFile(Path.of(testFilePathStr+"\\CloudServiceTest.txt"));
        //Files.write(testFilePath,"This is a test !!!");

    }
/*
    @Test
    void test() {

    }
*/
    //@AfterEach
    void finish() {
        if(file.exists()) {
            file.delete();
        }
    }


}
