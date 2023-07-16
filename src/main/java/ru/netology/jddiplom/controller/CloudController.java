package ru.netology.jddiplom.controller;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.netology.jddiplom.service.FileProperty;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CloudController {
    @GetMapping("/list")
    public ResponseEntity<?> getList(@RequestParam("limit") @Validated int limit)  {
        //System.out.println("logout : "+authToken.getLogin()+" "+authToken.getPassword());
        List<FileProperty> files = new ArrayList<>();
        files.add(new FileProperty("qwer",1000));
        files.add(new FileProperty("asdf",2000));
        files.add(new FileProperty("zxcv",3000));
        System.out.println("limit : "+limit);
        Gson gson = new Gson();
        String jsonArray = gson.toJson(files);
        return ResponseEntity.ok().body(jsonArray.toString());
    }

}
