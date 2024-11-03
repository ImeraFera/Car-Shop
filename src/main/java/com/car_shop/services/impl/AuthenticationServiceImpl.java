package com.car_shop.services.impl;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.car_shop.dto.AuthRequest;
import com.car_shop.dto.AuthResponse;
import com.car_shop.dto.DtoUser;
import com.car_shop.dto.RefreshTokenRequest;
import com.car_shop.enums.MessageType;
import com.car_shop.exceptions.BaseException;
import com.car_shop.exceptions.ErrorMessage;
import com.car_shop.jwt.JWTService;
import com.car_shop.models.RefreshToken;
import com.car_shop.models.User;
import com.car_shop.repositories.RefreshTokenRepository;
import com.car_shop.repositories.UserRepository;
import com.car_shop.services.IAuthenticationService;

@Service
public class AuthenticationServiceImpl implements IAuthenticationService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    private User createUser(AuthRequest input) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;

    }

    private RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setCreateTime(new Date());
        refreshToken.setExpireDate(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 4));
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        return refreshToken;
    }

    @Override
    public DtoUser register(AuthRequest input) {

        User savedUser = userRepository.save(createUser(input));

        DtoUser dtoUser = new DtoUser();

        BeanUtils.copyProperties(savedUser, dtoUser);

        return dtoUser;

    }

    @Override
    public AuthResponse authenticate(AuthRequest input) {

        try {

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    input.getUsername(), input.getPassword());

            authenticationProvider.authenticate(authenticationToken);

            Optional<User> optional = userRepository.findByUsername(input.getUsername());

            String accessToken = jwtService.generateToken(optional.get());

            RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(optional.get()));
            return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
        } catch (Exception e) {
            throw new BaseException(new ErrorMessage(MessageType.USERNAME_OR_PASSWORD_INVALID, e.getMessage()));
        }

    }

    public boolean isValidRefreshToken(Date expireDate) {
        return new Date().before(expireDate);
    }

    @Override
    public AuthResponse refreshToken(RefreshTokenRequest input) {
        Optional<RefreshToken> optional = refreshTokenRepository.findByRefreshToken(input.getRefreshToken());

        if (optional.isEmpty()) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_NOT_FOUND, input.getRefreshToken()));
        }

        if (!isValidRefreshToken(optional.get().getExpireDate())) {
            throw new BaseException(new ErrorMessage(MessageType.REFRESH_TOKEN_EXPIRED, input.getRefreshToken()));
        }

        User user = optional.get().getUser();
        String accessToken = jwtService.generateToken(user);

        RefreshToken savedRefreshToken = refreshTokenRepository.save(createRefreshToken(user));

        return new AuthResponse(accessToken, savedRefreshToken.getRefreshToken());
    }

}
