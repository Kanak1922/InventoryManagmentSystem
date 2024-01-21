package com.kanak.ims.service;

import com.kanak.ims.model.User;
import com.kanak.ims.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user=userRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException("User not exists by Username or Email"));

//        Set<GrantedAuthority> authorities=user
//                .map((role)-> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toSet());

       // return new org.springframework.security.core.userdetails.User(usernameOrEmail,user.getPassword(),authorities);
    return user;
    }
}
