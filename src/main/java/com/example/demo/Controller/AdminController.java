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

@CrossOrigin
@RequestMapping(value = "/admin")
@RestController
public class AdminController {

    @Value("${app.sessionLength}")
    private String sessionLengthString;

    @Value("${app.adminPassword}")
    private String actualPassword;

    @Value("${app.adminEmail}")
    private String actualEmail;



    @Autowired
    private AdminRepository repo;

    @CrossOrigin
    @PostMapping(path = "/signIn")
    public ResponseEntity<Object> adminSignIn(@RequestBody HashMap<String , String> map) {

        String password = map.get("password");
        String email = map.get("email");
        int sessionLength = Integer.parseInt(sessionLengthString);


        if (actualPassword.equals(password) && actualEmail.equals(email)) {

            // Add a session object
            Admin admin = new Admin(LocalDateTime.now().plusMinutes(sessionLength));
            repo.save(admin);

            return ResponseEntity.ok(admin.getId());
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Password");
    }

    @CrossOrigin
    @GetMapping(path= "/getSession/{id}")
    public ResponseEntity<Object> getSession(@PathVariable String id) {
        return ResponseEntity.ok(repo.findById(id));
    }

    @CrossOrigin
    @GetMapping(path= "/getAllSessions")
    public ResponseEntity<Object> getAllSessions() {
        return ResponseEntity.ok(repo.findAll());
    }

    @CrossOrigin
    @DeleteMapping(path = "/deleteSession/{id}")
    public ResponseEntity<Object> deleteSession(@PathVariable String id) {

        if (!repo.findById(id).isPresent())
            return ResponseEntity.notFound().build();

        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
