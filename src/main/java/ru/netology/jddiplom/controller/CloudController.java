package ru.netology.jddiplom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.jddiplom.service.AuthService;
import ru.netology.jddiplom.service.CloudService;



import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@CrossOrigin
public class CloudController {

    private final CloudService cloudService;

    private final AuthService authService;

    public CloudController(CloudService cloudService,AuthService authService ) {
        this.cloudService = cloudService;
        this.authService = authService;
    }


    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam("limit") @Validated int limit, @RequestHeader("Auth-Token") String token)  {
        System.out.println("List ...  token :"+token);
        if(authService.isActiveToken(token)) {
            cloudService.printCloud();
            return ResponseEntity.ok().body(cloudService.getList(authService.getAuthToken(token).getLogin()));
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/file")
    public ResponseEntity<?> uploadFile(@RequestParam("filename") @Validated String filename, @RequestHeader("Auth-Token") String token) throws FileNotFoundException {

        if(authService.isActiveToken(token)) {
            System.out.println("Post file ..." + filename);
            if (cloudService.addFile(filename, authService.getAuthToken(token).getLogin())) {
                return ResponseEntity.ok().body(cloudService.getResource(filename));
            } else
                return ResponseEntity.badRequest().body("Error loading file" + filename);
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/file")
    public ResponseEntity<?> downloadFile(@RequestParam("filename") @Validated String filename, @RequestHeader("Auth-Token") String token)  {
        System.out.println("Get file ..."+filename+" token "+token);
        if(authService.isActiveToken(token)) {
            Resource resource = cloudService.getResource(filename);
            if (resource != null) {
                return ResponseEntity.ok().body(resource);
            } else {
                return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/file")
    public ResponseEntity<?> renameFile(@RequestParam("filename")  String filename, @RequestParam("filename") String params, @RequestHeader("Auth-Token") String token) throws IOException {

        System.out.println("Put file ..."+filename+" token "+token+" params ... "+params);
        if(authService.isActiveToken(token)) {

            if (cloudService.renameFile(filename) == true) {
                return ResponseEntity.ok().build();
            } else {
                return new ResponseEntity<String>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
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