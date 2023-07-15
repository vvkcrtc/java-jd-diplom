package ru.netology.jddiplom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.netology.jddiplom.component.JwtTokenUtil;
import ru.netology.jddiplom.service.JwtUserDetailsService;

@RestController
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;

//    @Autowired
//    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;


    //@PostMapping
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthToken authToken) {
        System.out.println("request : "+authToken.getLogin()+" "+authToken.getPassword());
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(authToken.getLogin(), authToken.getPassword());
        try {

            authenticationManager.authenticate(token);

        } catch (BadCredentialsException e) {

            logger.error("Invalid credentials !!!", e);
            //throw e;
            return ResponseEntity.badRequest().body("\"message\","+"\"login:user1, password:12345\"");
            //new ResponseEntity<>("\"message\","+"\"login:user1, password:12345\"", HttpStatus.BAD_REQUEST);
        }

        //UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getLogin());

        //return ResponseEntity.ok("\"auth-token\" :"+authenticationRequest.toString());
       // return new ResponseEntity<>("\"auth-token\","+"\"login:user1, password:12345\"", HttpStatus.OK);
        //return ResponseEntity.ok().body("{\"auth-token :\","+"\"login:user1\", \"password:12345\"}");
        return ResponseEntity.ok().body(authToken);
    }



}