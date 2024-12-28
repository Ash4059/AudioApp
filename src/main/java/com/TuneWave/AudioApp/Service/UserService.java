package com.TuneWave.AudioApp.Service;

import com.TuneWave.AudioApp.Entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    void addUser(User user);
    User getUserById(Long Id);
    boolean updateUser(User user);
    boolean deleteUser(Long id);
    User getUserByUserName(String userName);
    boolean autheticateUser(String userName, String password);
    String generateToken(User user);
}
