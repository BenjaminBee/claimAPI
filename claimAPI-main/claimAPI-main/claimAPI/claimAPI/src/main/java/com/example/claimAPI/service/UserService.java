package com.example.claimAPI.service;

import com.example.claimAPI.exceptions.InvalidUserException;
import com.example.claimAPI.model.AuthenticationResponse;
//import com.example.claimAPI.model.user.LoginRequest;
import com.example.claimAPI.model.user.User;
import com.example.claimAPI.model.user.UserRepository;
import com.example.claimAPI.model.user.UserRequest;
import com.example.claimAPI.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Creates new user
    public boolean registerUser(UserRequest userReq) {
        Optional<User> testUser = userRepository.findByUsername(userReq.getUsername());
        if(testUser.isEmpty()) {
            String encodedPassword = bCryptPasswordEncoder.encode(userReq.getPassword());
            User user = new User(userReq.getUsername(), userReq.getEmail(), encodedPassword);
            userRepository.save(user);
            logger.trace("User details saved within user repository");
            return true;
        }
        logger.error("User exists within repository");
        return false;
    }

    //Logouts user from session
    //<<NOT IMPLEMENTED>>
    public ResponseEntity<?> logout(UserRequest userReq) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.remove("Authorization");
        return ResponseEntity.ok("Logged out");
    }
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User could not be found");
        }
        return user.get();
    }
    public ResponseEntity<?> authenticate(UserRequest auth) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword())
            );
            User user = userRepository.findByUsername(auth.getUsername()).get();
            final String jwt = jwtUtil.generateToken(user);
            logger.trace("Successful Login");
            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }
        catch(BadCredentialsException ex) {
            logger.error("Invalid username or password: " + ex);
            throw new InvalidUserException("Invalid user");
        }

    }
}