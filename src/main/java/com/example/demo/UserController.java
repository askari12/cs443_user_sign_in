package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

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
    public User getUserById(@PathVariable String id)
    {
        return userRepository.findById(id).get();
    }

    @PostMapping(value = "/createUser")
    public HashMap<String , String> createUser(@RequestBody User user)
    {
        HashMap<String , String> data = new HashMap<>();

        System.out.println(user.getPassword());



        if (userRepository.findById(user.getUserId()).orElse(null) == null) {
            userRepository.save(user);
            data.put("data" , "true");
            data.put("userId" , user.getUserId());
            return data;
        }

        data.put("data" , "false");
        return data;
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public void deleteUser(@PathVariable String id)
    {
        userRepository.deleteById(id);
    }

    @PutMapping(value = "/updateUser/{id}")
    public void updateUserName(@RequestBody User user, @PathVariable String id)
    {
        userRepository.updateUser(id, user);
    }


    // Check if user exists
    public void userExists() {
    }

    @PostMapping(value = "/checkPassword")
    public HashMap<String , String> checkPassword(@RequestBody HashMap<String , String> body) {

        String email = body.get("email");
        String password = body.get("password");

        HashMap<String , String> data = new HashMap<>();
        PasswordManager passwordManager = new PasswordManager();


        // Does user exist
        User user = userRepository.findById(email).orElse(null);

        if (user == null) {
            data.put("data" , "false");
            return data;
        }

        String hash = user.getPasswordHash();
        String salt = user.getPasswordSalt();

        if (!hash.equals( passwordManager.encodePassword(password , salt)) ) {
            data.put("data" , "false");
            return data;
        }

        data.put("data" , "true");
        data.put("userId", user.getUserId());
        return data;
    }
}
