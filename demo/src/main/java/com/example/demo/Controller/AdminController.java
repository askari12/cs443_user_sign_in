package com.example.demo.Controller;

import com.example.demo.Collections.Admin;
import com.example.demo.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;

@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    @Value("${app.sessionLength}")
    private String sessionLengthString;

    @Value("${app.adminPassword}")
    private String actualPassword;



    @Autowired
    private AdminRepository repo;

    @PostMapping(value = "/signUp")
    private ResponseEntity<Object> adminSignUp(@RequestBody HashMap<String , String> passwordMap) {

        String password = passwordMap.get("password");
        int sessionLength = Integer.parseInt(sessionLengthString);


        if (actualPassword.equals(password)) {

            // Add a session object
            Admin admin = new Admin(LocalDateTime.now().plusMinutes(sessionLength));
            repo.save(admin);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Password");
    }

    @GetMapping(value= "/getSession")
    private ResponseEntity<Object> getSession() {
        return ResponseEntity.ok(repo.findAll());
    }

}
