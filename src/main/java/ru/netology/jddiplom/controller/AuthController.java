package ru.netology.jddiplom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.jddiplom.component.JwtTokenUtil;
import ru.netology.jddiplom.service.JwtUserDetailsService;

@RestController
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

@Autowired
BCryptPasswordEncoder passwordEncoder;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthToken authenticationRequest) {
        System.out.println("request : "+authenticationRequest.getLogin()+" "+authenticationRequest.getPassword());
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(authenticationRequest.getLogin(), authenticationRequest.getPassword());
        try {
            authenticationManager.authenticate(token);
        } catch (BadCredentialsException e) {
            logger.error("Invalid credentials !!!", e);
            throw e;
        }

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getLogin());

        //return ResponseEntity.ok(new AuthResponse(jwtTokenUtil.generateToken(userDetails)));
        return ResponseEntity.ok(jwtTokenUtil.generateToken(userDetails));
    }

}