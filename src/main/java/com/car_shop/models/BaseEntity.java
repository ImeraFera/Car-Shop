package com.car_shop.models;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    // Bu class diğer tüm modellerdeki ortak(hepsinde olan fieldlar) olan fieldları
    // tutuyo id ve create time gibi.
    // bu yüzden tek bir classta bu fieldları tanımladık kod tekrarını önlemek için.
    // diğer tüm modeller bu classı extend etcek.

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "create_time")
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date createTime;

}
