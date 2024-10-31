package com.car_shop.models;

import java.math.BigDecimal;

import com.car_shop.enums.CarStatusType;
import com.car_shop.enums.CurrencyType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class Car extends BaseEntity {

    private String brand;

    private String plaka;

    private String model;

    @Column(name = "production_year")
    private Integer productionYear;

    private BigDecimal price;

    @Column(name = "currency_type")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Column(name = "damage_price")
    private BigDecimal damagePrice;

    @Column(name = "car_status_type")
    @Enumerated(EnumType.STRING)
    private CarStatusType carStatusType;

}
