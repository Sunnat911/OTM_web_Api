package com.example.OTM_web_api.service.userService;

import com.example.OTM_web_api.entity.userEntity.UserEntity;
import com.example.OTM_web_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
 

import java.util.Optional;


@Service
public class ApplicationUserDetailService implements UserDetailsService {


    private final UserRepository userRepository;

    @Autowired
    public ApplicationUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> optionalUserEntity = userRepository.findByUserName(username);
        if (optionalUserEntity.isEmpty())
            throw new UsernameNotFoundException(username + " this user not found, ");

        return (UserDetails) optionalUserEntity.get();
    }
}
