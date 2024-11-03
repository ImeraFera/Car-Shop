package com.car_shop.services;

import com.car_shop.dto.DtoAddress;
import com.car_shop.dto.DtoAddressIU;

public interface IAddressService {
    DtoAddress saveAddress(DtoAddressIU dtoAddressIU);

}
