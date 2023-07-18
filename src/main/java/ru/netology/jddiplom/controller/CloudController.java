package ru.netology.jddiplom.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.jddiplom.service.CloudService;
import ru.netology.jddiplom.service.FileProperty;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CloudController {

    @Autowired
    private CloudService cloudService;
    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam("limit") @Validated int limit)  {
        //System.out.println("logout : "+authToken.getLogin()+" "+authToken.getPassword());
        return ResponseEntity.ok().body(cloudService.getList());
    }
    @PostMapping("/file")
    public ResponseEntity<?> addFile(@RequestParam("filename") @Validated String filename)  {
        if(cloudService.addFile(filename)) {
            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.badRequest().body("Error loading file"+filename);
    }

    @GetMapping("/file")
    public ResponseEntity<?> getFile(@RequestParam("filename") @Validated String filename)  {

            return ResponseEntity.ok().build();

    }

}
