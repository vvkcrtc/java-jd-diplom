package ru.netology.jddiplom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthToken authToken)  {
        System.out.println("request : "+authToken.getLogin()+" "+authToken.getPassword());
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(authToken.getLogin(), authToken.getPassword());
        try {

            authenticationManager.authenticate(token);

        } catch (BadCredentialsException e) {

            logger.error("Invalid credentials !!!", e);

            return ResponseEntity.badRequest().body("\"message\","+"\"login:user1, password:12345\"");

        }
        AuthResponse ar = new AuthResponse(authToken);

        return ResponseEntity.ok().body(authToken.toString());
        //return ResponseEntity.ok().body(authToken);
        //return ResponseEntity.ok().body(ar);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> userLogout(@RequestBody String value)  {
        System.out.println("post value : "+value);
        return ResponseEntity.ok().body("Success  logout");
    }

    @GetMapping("/login")
    public ResponseEntity<?> getLogout(@RequestParam("logout") @RequestBody String value)  {
        System.out.println("value : "+value);
        return ResponseEntity.ok().body("Success  logout");
    }


}