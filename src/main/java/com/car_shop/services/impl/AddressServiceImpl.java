package com.car_shop.services.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car_shop.dto.DtoAddress;
import com.car_shop.dto.DtoAddressIU;
import com.car_shop.models.Address;
import com.car_shop.repositories.AddressRepository;
import com.car_shop.services.IAddressService;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(DtoAddressIU dtoAddressIU) {
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU, address);

        return address;
    }

    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));

        DtoAddress dtoAddress = new DtoAddress();

        BeanUtils.copyProperties(savedAddress, dtoAddress);

        return dtoAddress;
    }

}
