package com.TuneWave.AudioApp.User;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
    void addUser(User user);
    User getUserById(Long Id);
    boolean updateUser(User user);
    boolean deleteUser(Long id);
}
