package org.tsp.eshopplum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tsp.eshopplum.entities.LoginReq;
import org.tsp.eshopplum.entities.LoginResponse;
import org.tsp.eshopplum.entities.User;
import org.tsp.eshopplum.entities.enums.Role;
import org.tsp.eshopplum.services.UserService;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class SignController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/reg")
    public ResponseEntity<User> addNewUser(@RequestBody Map<String, String> userreg){
        User user = new User(null, userreg.get("name"), userreg.get("email"), userreg.get("phone"), userreg.get("password"), Role.USER);
        user = userService.save(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginReq loginReq){
        LoginResponse loginResponse = userService.loginUser(loginReq);
        return ResponseEntity.ok().body(loginResponse);
    }

}
