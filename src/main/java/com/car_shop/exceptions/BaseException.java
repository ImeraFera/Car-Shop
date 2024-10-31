package com.car_shop.exceptions;

public class BaseException extends RuntimeException {

    public BaseException(ErrorMessage errorMessage) {

        super(errorMessage.prepareErrorMessage());

    }
}
