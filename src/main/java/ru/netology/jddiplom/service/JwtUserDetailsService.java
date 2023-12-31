package ru.netology.jddiplom.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.jddiplom.repository.UserData;
import ru.netology.jddiplom.repository.UsersRepository;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserData userData = usersRepository.findByLogin(username);
        if (userData == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        UserDetails ud = User.builder()
                .username(userData.getLogin())
                .password(userData.getPassword())
                .roles(userData.getRole())
                .build();
        return ud;
    }

}