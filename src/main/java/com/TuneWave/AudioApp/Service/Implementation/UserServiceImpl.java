package com.TuneWave.AudioApp.Service.Implementation;

import com.TuneWave.AudioApp.Entity.User;
import com.TuneWave.AudioApp.Repository.UserRepository;
import com.TuneWave.AudioApp.Service.UserService;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, JWTService jwtService, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long Id) {
        return userRepository.findById(Id).orElse(null);
    }

    @Override
    public boolean updateUser(User user) {
        return userRepository.findById(user.getId()).map(oUser -> {
            oUser.setFirstName(user.getFirstName());
            oUser.setLastName(user.getLastName());
            oUser.setUserName(user.getUserName());
            oUser.setEmailId(user.getEmailId());
            oUser.setBirthDate(user.getBirthDate());
            oUser.setCountry(user.getCountry());
            if (user.getPassword() != null && !user.getPassword().isEmpty()) {
                oUser.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            userRepository.save(oUser);
            return true;
        }).orElse(false);
    }

    @Override
    public boolean deleteUser(Long Id) {
        return userRepository.findById(Id).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUserName(userName).orElse(null);
    }

    @Override
    public String VerifyUser(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword())
        );
        if (authentication.isAuthenticated()) {
            return jwtService.GenerateToken(user);
        }
        throw new NoSuchElementException("User not found with username: " + user.getUserName());
    }
}
