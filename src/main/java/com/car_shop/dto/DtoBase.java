package com.car_shop.dto;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoBase {

    private UUID id;
    private Date createTime;

}
