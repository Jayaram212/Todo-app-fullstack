package com.jayaram.full_stack_backend.controller;

import com.jayaram.full_stack_backend.exception.UserNotFoundException;
import com.jayaram.full_stack_backend.model.User;
import com.jayaram.full_stack_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/user")
    public ResponseEntity<User> newUser(@RequestBody User newUser)
    {
        User saveUser =userRepository.save(newUser);
        return  new ResponseEntity<>(saveUser,HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers()
    {
        List<User> user =userRepository.findAll();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id) .orElseThrow(() -> new UserNotFoundException(id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User newUser,@PathVariable Long id){
        return userRepository.findById(id)
                .map(user ->{
                    user.setUsername(newUser.getUsername());
                    user.setName(newUser.getName());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return new ResponseEntity<>( "User with ID " + id + " has been deleted successfully", HttpStatus.OK );
    }
}

