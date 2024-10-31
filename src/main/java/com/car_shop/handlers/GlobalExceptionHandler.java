package com.car_shop.handlers;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.car_shop.exceptions.BaseException;

import lombok.val;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { BaseException.class })

    public ResponseEntity<ApiError<?>> handleBaseException(BaseException ex, WebRequest request) {
        return ResponseEntity.badRequest().body(createApiError(ex.getMessage(), request));
    }

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<ApiError<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
            WebRequest request) {

        Map<String, List<String>> errorsMap = new HashMap<>();

        for (ObjectError errorObj : ex.getBindingResult().getAllErrors()) {
            String fieldName = ((FieldError) errorObj).getField();

            if (errorsMap.containsKey(fieldName)) {

                errorsMap.put(fieldName, addToList(errorsMap.get(fieldName), errorObj.getDefaultMessage()));

            } else {
                errorsMap.put(fieldName, addToList(new ArrayList<>(), errorObj.getDefaultMessage()));
            }
        }

        return ResponseEntity.badRequest().body(createApiError(null, request));

    }

    private List<String> addToList(List<String> errors, String errorMessage) {

        errors.add(errorMessage);

        return errors;
    }

    private String getHostName() {
        try {
            return Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
        }
        return "";
    }

    public <E> ApiError<E> createApiError(E message, WebRequest request) {
        ApiError<E> apiError = new ApiError<>();
        apiError.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        Exception<E> exception = new Exception<>();

        exception.setPath(request.getDescription(false).substring(4));
        exception.setCreateTime(new Date());
        exception.setMessage(message);
        exception.setHostName(getHostName());

        apiError.setException(exception);

        return apiError;

    }
}
