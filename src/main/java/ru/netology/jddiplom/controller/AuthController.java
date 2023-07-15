package ru.netology.jddiplom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.netology.jddiplom.service.JwtUserDetailsService;

import java.io.UnsupportedEncodingException;

@RestController
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

   // @Autowired
   // private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;


    //@PostMapping
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthToken authToken)  {
        System.out.println("request : "+authToken.getLogin()+" "+authToken.getPassword());
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(authToken.getLogin(), authToken.getPassword());
        try {

            authenticationManager.authenticate(token);

        } catch (BadCredentialsException e) {

            logger.error("Invalid credentials !!!", e);
            //throw e;
            return ResponseEntity.badRequest().body("\"message\","+"\"login:user1, password:12345\"");

        }

        //UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authToken.getLogin());
        //String tokenOut = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok().body("{\"auth-token\""+":"+"\""+authToken.getLogin()+","+authToken.getPassword()+"\""+"}");
        //{"auth-token":"login:12345, password:1345"}
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<?> userLogout(@RequestBody AuthToken authToken)  {
        System.out.println("logout : "+authToken.getLogin()+" "+authToken.getPassword());

        return ResponseEntity.ok().body("Success  logout");
    }
}