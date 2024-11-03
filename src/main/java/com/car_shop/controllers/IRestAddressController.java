package com.car_shop.controllers;

import com.car_shop.dto.DtoAddress;
import com.car_shop.dto.DtoAddressIU;

public interface IRestAddressController {

    RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
