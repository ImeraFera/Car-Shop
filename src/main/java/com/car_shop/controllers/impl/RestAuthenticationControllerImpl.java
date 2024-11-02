package com.car_shop.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.car_shop.controllers.IRestAuthenticationController;
import com.car_shop.controllers.RestBaseController;
import com.car_shop.controllers.RootEntity;
import com.car_shop.dto.AuthRequest;
import com.car_shop.dto.DtoUser;
import com.car_shop.services.IAuthenticationService;

import jakarta.validation.Valid;

@RestController
public class RestAuthenticationControllerImpl extends RestBaseController implements IRestAuthenticationController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/register")
    @Override
    public RootEntity<DtoUser> register(@Valid @RequestBody AuthRequest input) {
        return ok(authenticationService.register(input));
    }

}