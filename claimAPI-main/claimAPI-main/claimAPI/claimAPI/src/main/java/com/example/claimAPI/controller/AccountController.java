package com.example.claimAPI.controller;
import com.example.claimAPI.model.AuthenticationResponse;
//import com.example.claimAPI.model.user.LoginRequest;
import com.example.claimAPI.model.user.User;
import com.example.claimAPI.model.user.UserRepository;
import com.example.claimAPI.model.user.UserRequest;
import com.example.claimAPI.service.UserService;
import com.example.claimAPI.util.JwtUtil;
import com.example.claimAPI.util.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(AccountController.class);


    //Register new user into system
    @PostMapping(path="/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRequest userRequest) {
        if(userService.registerUser(userRequest)) {
            logger.trace("Successful Registration");
            return ResponseEntity.ok("Registration Successful");
        }
        logger.trace("Unsuccessful Registration");
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Not accepted or user exists already");
    }

//    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    @PostMapping(path="/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserRequest auth) {
        return userService.authenticate((auth));
    }

    //Logout the user session
    //<<NOT IMPLEMENTED>>
    @PostMapping(path="/logout")
    public ResponseEntity<?> logout(@RequestBody UserRequest userRequest) {
       // if(userService.logout(userRequest)) {
           // logger.trace("Successfully Logged Out");
            return ResponseEntity.ok("Logged Out");
        //}
       // logger.trace("Unsuccessfully Logged Out");
        //return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Logout Failed");
    }

    //returns a list of all users and their details
    @GetMapping(path="/getUsers")
    public ResponseEntity<?> getUsers() {
        Iterable<User> iterable = userRepository.findAll();
        if(iterable == null) {
            logger.trace("Could not find users");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found");
        }
        logger.trace("Returned list of users successfully");
        return ResponseEntity.ok(iterable);
    }
}