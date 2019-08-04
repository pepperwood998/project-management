package com.tuan.exercise.projman.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tuan.exercise.projman.config.AccountInfo;
import com.tuan.exercise.projman.entity.Account;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        Account acc = findUserbyUername(username);

        UserBuilder builder = null;
        if (acc != null) {
            builder = User.withUsername(username);
            builder.password(new BCryptPasswordEncoder().encode(acc.getPassword()));
            builder.roles(acc.getRoles());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }

    private Account findUserbyUername(String username) {

        Optional<Account> account = AccountInfo.getAccountList().stream()
                .filter(acc -> acc.getUsername().equals(username)).findFirst();

        return account.orElse(null);
    }
}
