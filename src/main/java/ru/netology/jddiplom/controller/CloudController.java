package ru.netology.jddiplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
//import org.springframework.boot.json.JsonParser;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.netology.jddiplom.component.JwtTokenUtil;
import ru.netology.jddiplom.service.JwtUserDetailsService;
//import ru.netology.jddiplom.repository.UsersRepository;
//import ru.netology.jddiplom.service.CloudService;

@CrossOrigin
@RestController
public class CloudController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    //private CloudService cloudService;
    //public CloudController(CloudService cloudService) {
    //    this.cloudService = cloudService;
    //}
/*
    @RequestMapping("/login")
    @PostMapping
    public ResponseEntity<String> getResponse(@RequestBody String request) throws JSONException {
        System.out.println(request);
        JSONObject jsonObject = new JSONObject(request);
        System.out.println(jsonObject);
        JSONObject resp = new JSONObject();
        resp.put("auth-token","login:12345, password:1345");
      //  System.out.println(cloudService.getAll());
        return new ResponseEntity<>(resp.toString(), HttpStatus.OK);

    }


 */
/*
    @RequestMapping("/login")
    @PostMapping
    public ResponseEntity getResponse(@RequestBody AuthToken authToken) {
        System.out.println("request : "+authToken.getLogin()+" "+authToken.getPassword());
//        System.out.println();
        //  System.out.println(cloudService.getAll());
        //return new ResponseEntity<>("\"auth-token\","+"\"login:12345, password:1345\"", HttpStatus.OK);
        return new ResponseEntity(authToken, HttpStatus.OK);

    }
*/

 /*
    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthToken authToken) {
        System.out.println("request : "+authToken.getLogin()+" "+authToken.getPassword());

//var tmp = new UsernamePasswordAuthenticationToken(authToken.getLogin(), authToken.getPassword());
//        Authentication auth = authenticationManager.authenticate(tmp);

//        User user1 = (User) auth.getPrincipal();
        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    authToken.getLogin(), authToken.getPassword()
                            )
                    );

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok()
                    .header(
                            HttpHeaders.AUTHORIZATION,
                            jwtTokenUtil.generateToken(user)
                    )
                    .body(authToken.toString());
        } catch (BadCredentialsException ex) {
            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }


  */


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthToken authToken) throws Exception {
        System.out.println("request : "+authToken.getLogin()+" "+authToken.getPassword());
        authenticate(authToken.getLogin(), authToken.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authToken.getLogin());

        final String token = jwtTokenUtil.generateToken(userDetails);

        //return ResponseEntity.ok(new JwtResponse(token));
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.AUTHORIZATION,
                        jwtTokenUtil.generateToken(userDetails)
                )
                .body(authToken.toString());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

}
