package com.TuneWave.AudioApp.Controller;

import com.TuneWave.AudioApp.Entity.User;
import com.TuneWave.AudioApp.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return new ResponseEntity<>("User added successfully!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User user) {
        String token = null;
        StringBuilder message = new StringBuilder("Login successful!");
        HttpStatus status = HttpStatus.OK;
        try {
            token = userService.VerifyUser(user);
        } catch (NoSuchElementException e) {
            status = HttpStatus.UNAUTHORIZED;
            message = new StringBuilder(e.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            message = new StringBuilder("An unexpected error occurred.");
        }
        ResponseEntity<String> response;
        if (token != null) {
            response = ResponseEntity.status(status)
                    .header("Authorization", token)
                    .body(message.toString());
        } else {
            response = ResponseEntity.status(status)
                    .body(message.toString());
        }
        return response;
    }

    @GetMapping("/{Id}")
    public ResponseEntity<User> findUserBId(@PathVariable Long Id) {
        User user = userService.getUserById(Id);
        if (user == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody User user) {
        if (userService.updateUser(user)) {
            return new ResponseEntity<>("User updated successfully!", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long Id) {
        if (userService.deleteUser(Id)) {
            return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    }
}
