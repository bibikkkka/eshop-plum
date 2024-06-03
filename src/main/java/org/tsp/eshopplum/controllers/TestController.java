package org.tsp.eshopplum.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.tsp.eshopplum.entities.User;
import org.tsp.eshopplum.services.UserService;

@RestController
@RequestMapping("api/v1/test")
public class TestController {

    private final UserService userService;

    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/welcome")
    public String welcome() {
        //UNPROTECTED PAGE
        return "Welcome to the Shop Plum!";
    }

    /*@PostMapping("/new")
    public String addNewUser(@RequestBody User user) {
        userService.save(user);
        return "User is saved";
    }*/

    @GetMapping("/home")
    @PreAuthorize("hasAuthority('USER')") // описываем правила для захода на эту точку
    public String home() {
        //PROTECTED PAGE
        return "Make yourself at home, dear!";
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        //PROTECTED PAGE
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

}
