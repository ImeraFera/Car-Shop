package com.car_shop.controllers;

import com.car_shop.dto.AuthRequest;
import com.car_shop.dto.AuthResponse;
import com.car_shop.dto.DtoUser;
import com.car_shop.dto.RefreshTokenRequest;
import com.car_shop.models.RefreshToken;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);

    public RootEntity<AuthResponse> authenticate(AuthRequest input);

    public RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);

}
