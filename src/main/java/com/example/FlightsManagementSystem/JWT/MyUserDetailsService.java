package com.example.FlightsManagementSystem.JWT;


import com.example.FlightsManagementSystem.dao.Users;
import com.example.FlightsManagementSystem.poco.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private Users userDao=new Users();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var userRes = userDao.getRoleAndPassByUsername(username);
        String ROLE_PREFIX ="ROLE_";
        try {
            userRes.next();
        } catch (SQLException e) {
            throw new UsernameNotFoundException("Could not findUser with username = " + username);
        }


        try {
            return new org.springframework.security.core.userdetails.User(
                    username,
                   userRes.getString("password"),
                    Collections.singletonList(new SimpleGrantedAuthority(ROLE_PREFIX+userRes.getString("role_name"))));
        } catch (SQLException e) {
            throw new UsernameNotFoundException("Should not happen");
        }
        finally {
            userDao.closeAll();
        }
    }
}