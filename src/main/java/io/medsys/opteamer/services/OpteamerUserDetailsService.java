package io.medsys.opteamer.services;

import io.medsys.opteamer.model.OpteamerUserDetails;
import io.medsys.opteamer.model.User;
import io.medsys.opteamer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("OpteamerUserDetailsService")
public class OpteamerUserDetailsService implements UserDetailsService {
    UserRepository userRepository;
    @Autowired
    public OpteamerUserDetailsService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username){
        User user=userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("User Not Found with username: "+username));
        return OpteamerUserDetails.build(user);
    }
}
