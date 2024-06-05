package org.tsp.eshopplum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
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
        System.out.println("\n###########\n" + "User saved" + "\n###########\n");

        return ResponseEntity.created(uri).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        System.out.println("\n###########" + loginRequest.getUsername() + " " + loginRequest.getPassword() + "\n###########\n");
        // Находим пользователя по имени пользователя
        User user = (User) userService.loadUserByUsername(loginRequest.getUsername());

        // Проверяем, существует ли пользователь и соответствует ли введенный пароль
        if (user != null && passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {

            System.out.println("\n###########\n" + "User login" + "\n###########\n");
            return ResponseEntity.ok("Вход выполнен успешно!");

        } else {
            System.out.println("\n###########\n" + "User doesn't login" + "\n###########\n");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неверные имя пользователя или пароль");
        }
    }
    //ошибка в LoginRequest
    public class LoginRequest {

        private String username;
        private String password;

        public LoginRequest() {
        }

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        // геттеры и сеттеры
    }


}
