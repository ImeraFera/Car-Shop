package com.car_shop.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")

    private String lastName;

    private String tcno;

    private Date birthday;

    @OneToOne
    private Address address;

    @OneToOne
    private Account account;

}
