package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public User getUserById(@PathVariable long id)
    {
        return userRepository.findById(id).get();
    }

    @PostMapping(value = "/createUser")
    public void createUser(@RequestBody User user)
    {
        User newUser = userRepository.save(user);
        System.out.println(newUser);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public void deleteUser(@PathVariable long id)
    {
        userRepository.deleteById(id);
    }

    @PutMapping(value = "/updateUser/{id}")
    public void updateUserName(@RequestBody User user, @PathVariable long id)
    {
        userRepository.updateUser(id, user);
    }


    // Check if user exists
    public void userExists() {
    }
}
