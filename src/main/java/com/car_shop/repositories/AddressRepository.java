package com.car_shop.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.car_shop.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

}
