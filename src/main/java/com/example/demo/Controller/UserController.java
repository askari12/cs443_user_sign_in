package com.example.demo.Controller;

import com.example.demo.Collections.User;
import com.example.demo.JsonObj.FilteredUser;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.util.PasswordManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable String id) {

        Optional<User> temp = userRepository.findById(id);

        if (!temp.isPresent())
            return ResponseEntity.notFound().build();

        User user = temp.get();
        FilteredUser fuser = new FilteredUser(user.getUserId() , user.getFirstName() , user.getLastName() , user.getEmail());

        return ResponseEntity.ok( fuser );
    }

    @PostMapping(value = "/createUser")
    public ResponseEntity<Object> createUser(@RequestBody User user) {

        // Make JSON Object
        HashMap<String , String> data = new HashMap<>();

        if (userRepository.findById(user.getUserId()).orElse(null) == null) {
            userRepository.save(user);
            data.put("userId" , user.getUserId());
            return ResponseEntity.ok(data);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Selected User already Exists !");
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable String id) {
        if (!userRepository.findById(id).isPresent())
            return ResponseEntity.notFound().build();

        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity<Object> updateUserName(@RequestBody User user, @PathVariable String id) {

        if (!userRepository.findById(id).isPresent())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User Name is already Taken !");

        userRepository.updateUser(id, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/checkPassword")
    public ResponseEntity<Object> checkPassword(@RequestBody HashMap<String , String> body) {

        String email = body.get("email");
        String password = body.get("password");

        HashMap<String , String> data = new HashMap<>();
        PasswordManager passwordManager = new PasswordManager();


        // Does user exist
        User user = userRepository.findById(email).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        String hash = user.getPasswordHash();
        String salt = user.getPasswordSalt();

        if (!hash.equals( passwordManager.encodePassword(password , salt)) ) {
            data.put("data" , "false");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Credentials");
        }

        data.put("userId", user.getUserId());
        return ResponseEntity.ok(data);
    }

    @GetMapping(value = "/numUsers")
    public ResponseEntity<Object> numberOfUsers() {
        return ResponseEntity.ok(userRepository.count());
    }

}
