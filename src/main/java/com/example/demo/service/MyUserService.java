package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.MyUserDetails;
import com.example.demo.entity.User;
import com.example.demo.repository.IUserAccountDao;

@Service
public class MyUserService implements UserDetailsService{
	
private final IUserAccountDao dao;
    
    @Autowired
    public MyUserService(IUserAccountDao dao) {
        this.dao = dao;
    }
    
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         
        Optional<User> user = dao.findUser(username);
        if(!user.isPresent()) {
            throw new UsernameNotFoundException(username + "が存在しません");
        }   
        return new MyUserDetails(user.get());
    }

}
