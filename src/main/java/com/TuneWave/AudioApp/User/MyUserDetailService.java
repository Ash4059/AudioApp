package com.TuneWave.AudioApp.User;

import com.TuneWave.AudioApp.User.impl.UserServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

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

    public static BCryptPasswordEncoder getPasswordEncoder() throws NoSuchAlgorithmException, NoSuchProviderException {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2B, 12,
                SecureRandom.getInstance("SHA1PRNG","SUN"));
    }
}
