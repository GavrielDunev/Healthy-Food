package com.example.healthyfood.service.impl;

import com.example.healthyfood.model.entity.UserEntity;
import com.example.healthyfood.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthyFoodUserServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public HealthyFoodUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username
                        + " was not found !"));

        return mapToDetails(userEntity);
    }

    private static UserDetails mapToDetails(UserEntity userEntity) {

        List<GrantedAuthority> authorities = userEntity.getRoles()
                .stream()
                .map(userRoleEntity -> new SimpleGrantedAuthority("ROLE_" + userRoleEntity.getRole().name()))
                .collect(Collectors.toList());

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
