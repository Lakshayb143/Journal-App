package com.loopbreaker.JApp.controller;



import com.loopbreaker.JApp.entity.User;
import com.loopbreaker.JApp.servies.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAll();
    }


    @PostMapping("/create")
    public void createUser(@RequestBody User user)
    {
        userService.saveEntry(user);
    }

}