package ru.netology.jddiplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.jddiplom.service.CloudService;
import ru.netology.jddiplom.service.FileSystemStorageService;

import java.io.FileNotFoundException;

@RestController
@CrossOrigin
public class CloudController {

    @Autowired
    private CloudService cloudService;

    @Autowired
    private FileSystemStorageService storageService;

    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam("limit") @Validated int limit)  {
        cloudService.printCloud();
        return ResponseEntity.ok().body(cloudService.getList());
    }
    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestParam("filename") @Validated String filename) throws FileNotFoundException {

        Resource resource = storageService.loadAsResource(filename);


        System.out.println("Post file ..."+filename);
        if(cloudService.addFile(resource)) {

            return ResponseEntity.ok().body(resource);
        }
        else
            return ResponseEntity.badRequest().body("Error loading file"+filename);
    }

    @GetMapping("/file")
    public ResponseEntity<?> downloadFile(@RequestParam("filename") @Validated String filename, @RequestHeader("Auth-Token") String token)  {
        System.out.println("Get file ..."+filename+" token "+token);
        Resource resource =  cloudService.getFileData(filename);
        if(resource != null) {
            return ResponseEntity.ok().body(resource);
        }
        else {
            return ResponseEntity.badRequest().body("Error loading file from cloud "+filename);
        }

    }

    @DeleteMapping("/file")
    public ResponseEntity<?> deleteFile(@RequestParam("filename") @Validated String filename) {
        if(cloudService.deleteFile(filename)) {
            return ResponseEntity.ok().build();
        }
        else {
            return ResponseEntity.badRequest().body("Error delete file"+filename);
        }
    }


}