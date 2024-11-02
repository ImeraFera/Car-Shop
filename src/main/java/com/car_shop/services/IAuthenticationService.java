package com.car_shop.services;

import com.car_shop.dto.AuthRequest;
import com.car_shop.dto.DtoUser;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);
}
