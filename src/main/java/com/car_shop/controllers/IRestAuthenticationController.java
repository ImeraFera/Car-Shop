package com.car_shop.controllers;

import com.car_shop.dto.AuthRequest;
import com.car_shop.dto.DtoUser;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);
}
