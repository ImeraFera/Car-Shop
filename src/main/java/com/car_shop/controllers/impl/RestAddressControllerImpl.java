package com.car_shop.controllers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.car_shop.controllers.IRestAddressController;
import com.car_shop.controllers.RestBaseController;
import com.car_shop.controllers.RootEntity;
import com.car_shop.dto.DtoAddress;
import com.car_shop.dto.DtoAddressIU;
import com.car_shop.services.IAddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/address")
public class RestAddressControllerImpl extends RestBaseController implements IRestAddressController {

    @Autowired
    private IAddressService addressService;

    @Override
    @PostMapping("/save")
    public RootEntity<DtoAddress> saveAddress(@Valid @RequestBody DtoAddressIU dtoAddressIU) {
        return ok(addressService.saveAddress(dtoAddressIU));
    }

}
