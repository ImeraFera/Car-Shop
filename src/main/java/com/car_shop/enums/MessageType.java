package com.car_shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum MessageType {

    NO_RECORD_EXIST("1004", "Kayıt Bulunamadı."),
    GENERAL_EXCEPTION("9999", "Genel Hata.");

    private String code;
    private String message;

}
