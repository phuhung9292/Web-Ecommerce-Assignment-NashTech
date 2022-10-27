package com.example.demo.security.userPrincal;

import com.example.demo.Entity.TblUserEntity;
import com.example.demo.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    IUserRepository userRepository;
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TblUserEntity user = userRepository.findTblUserEntitiesByEmail(username).orElseThrow(() ->  new UsernameNotFoundException("User not found -> email + pasword"+username));
        return UserPrinciple.build(user);
    }
}
