package ru.netology.jddiplom.service;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.DataValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.netology.jddiplom.repository.UserData;
import ru.netology.jddiplom.repository.UsersRepository;

import java.util.ArrayList;
import java.util.Collection;

/*
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserDetails user = usersRepository.findByLogin(username);
        UserData userData = usersRepository.findByLogin(username);
                if (userData == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }
        UserDetails ud = User.builder()
                .username(userData.getLogin())
                .password(userData.getPassword())
                .roles(userData.getRole())
                .build();
        return ud;
    }

}
*/
@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //UserDAO user = userRepository.findByUsername(username);
        UserData userData = usersRepository.findByLogin(username);
        if (userData == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new User(userData.getLogin(), userData.getPassword(), new ArrayList<>());
    }
}