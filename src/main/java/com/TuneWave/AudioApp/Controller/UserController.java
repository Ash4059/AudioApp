package com.TuneWave.AudioApp.Controller;

import com.TuneWave.AudioApp.DTO.UserDTO;
import com.TuneWave.AudioApp.Entity.User;
import com.TuneWave.AudioApp.Service.Implementation.JWTService;
import com.TuneWave.AudioApp.Service.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JWTService jwtService;

    public UserController(UserService userService, JWTService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        HttpStatus responseStatus = HttpStatus.OK;
        String jwtToken = "";
        UserDTO userDTO = null;

        try {
            // Check if user already exists
            if (userService.getUserByUserName(user.getUserName()) != null) {
                throw new RuntimeException("User already exists");
            }

            // Add the user
            userService.addUser(user);

            // Generate JWT token
            jwtToken = jwtService.GenerateToken(user);

            // Map User to UserDTO
            userDTO = new UserDTO(user);

        } catch (Exception e) {
            responseStatus = HttpStatus.CONFLICT; // Conflict status for user already exists
            return ResponseEntity.status(responseStatus).body(e.getMessage());
        }

        return ResponseEntity.status(responseStatus)
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .body(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String userName, @RequestParam String password) {
        HttpStatus status;
        String token;
        try {
            if (userService.autheticateUser(userName, password)) {
                User user = userService.getUserByUserName(userName);
                token = jwtService.GenerateToken(user);
                UserDTO userDTO = new UserDTO(user);
                status = HttpStatus.OK;
                return ResponseEntity.status(status).header(HttpHeaders.AUTHORIZATION, token).body(userDTO);
            } else {
                status = HttpStatus.UNAUTHORIZED;
                return ResponseEntity.status(status).body("Invalid username or password");
            }
        } catch (NoSuchElementException e) {
            status = HttpStatus.UNAUTHORIZED;
            return ResponseEntity.status(status).body(e.getMessage());
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return ResponseEntity.status(status).body("An error occurred while processing the request");
        }
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
