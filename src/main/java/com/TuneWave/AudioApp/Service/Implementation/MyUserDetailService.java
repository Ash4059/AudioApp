package com.TuneWave.AudioApp.Service.Implementation;

import com.TuneWave.AudioApp.Entity.User;
import com.TuneWave.AudioApp.Entity.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserServiceImpl userService;

    public MyUserDetailService(UserServiceImpl userService){
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if(user == null){
            throw new UsernameNotFoundException("No such user present");
        }
        return new UserPrincipal(user);
    }
}
