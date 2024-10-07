package com.TuneWave.AudioApp.User.impl;

import com.TuneWave.AudioApp.User.MyUserDetailService;
import com.TuneWave.AudioApp.User.User;
import com.TuneWave.AudioApp.User.UserRepository;
import com.TuneWave.AudioApp.User.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getUserById(Long Id) {
        Optional<User> artistOptional = userRepository.findById(Id);
        return artistOptional.orElse(null);
    }

    @Override
    public boolean updateUser(User user) {
        Optional<User> artistOptional = userRepository.findById(user.getId());
        if(artistOptional.isPresent()){
            User oUser = artistOptional.get();
            oUser.setFirstName(user.getFirstName());
            oUser.setLastName(user.getLastName());
            oUser.setUserName(user.getUserName());
            oUser.setEmailId(user.getEmailId());
            oUser.setPassword(encodePassword(user.getPassword()));
            oUser.setAge(user.getAge());
            oUser.setCountry(user.getCountry());
            userRepository.save(oUser);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Long Id) {
        Optional<User> artistOptional = userRepository.findById(Id);
        if(artistOptional.isPresent()){
            userRepository.deleteById(Id);
            return  true;
        }
        return false;
    }

    @Override
    public User getUserByUserName(String userName){
        Optional<User> userOptional = userRepository.findByUserName(userName);
        return userOptional.orElse(null);
    }

    public String encodePassword(String password){
        StringBuilder Password = new StringBuilder();
        BCryptPasswordEncoder encoder = User.getPasswordEncoder();
        Password.append(encoder.encode(password));
        return Password.toString();
    }
}
