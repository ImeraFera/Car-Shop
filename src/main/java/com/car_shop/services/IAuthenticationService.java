package com.car_shop.services;

import com.car_shop.dto.AuthRequest;
import com.car_shop.dto.AuthResponse;
import com.car_shop.dto.DtoUser;
import com.car_shop.dto.RefreshTokenRequest;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);

    public AuthResponse refreshToken(RefreshTokenRequest input);
}
