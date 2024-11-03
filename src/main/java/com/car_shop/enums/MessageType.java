package com.car_shop.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum MessageType {

    NO_RECORD_EXIST("1004", "Kayıt Bulunamadı."),
    GENERAL_EXCEPTION("9999", "Genel Hata."),
    TOKEN_IS_EXPIRED("1005", "Token Süresi Doldu."),
    USERNAME_NOT_FOUND("1006", "Username Bulunamadı."),
    REFRESH_TOKEN_NOT_FOUND("1007", "Refresh Token Bulunamadı."),
    REFRESH_TOKEN_EXPIRED("1008", "Refresh Token Süresi Dolmuş."),
    USERNAME_OR_PASSWORD_INVALID("1009", "Username Veya Password Geçersiz.");

    private String code;
    private String message;

}
