package com.car_shop.services.impl;

import org.springframework.stereotype.Service;

import com.car_shop.enums.MessageType;
import com.car_shop.exceptions.BaseException;
import com.car_shop.exceptions.ErrorMessage;
import com.car_shop.services.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

    public void test() {
        // throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, null));
    }

}
