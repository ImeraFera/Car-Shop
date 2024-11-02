package com.car_shop.services.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.car_shop.dto.AuthRequest;
import com.car_shop.dto.DtoUser;
import com.car_shop.models.User;
import com.car_shop.repositories.UserRepository;
import com.car_shop.services.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private User createUser(AuthRequest input) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;

    }

    @Override
    public DtoUser register(AuthRequest input) {

        User savedUser = userRepository.save(createUser(input));

        DtoUser dtoUser = new DtoUser();

        BeanUtils.copyProperties(savedUser, dtoUser);

        return dtoUser;

    }

}
