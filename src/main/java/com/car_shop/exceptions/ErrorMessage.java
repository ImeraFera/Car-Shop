package com.car_shop.exceptions;

import com.car_shop.enums.MessageType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {

    private String ofStatic;

    private MessageType messageType;

    public ErrorMessage(MessageType messageType, String ofStatic) {
        this.messageType = messageType;
        this.ofStatic = ofStatic;

    }

    public String prepareErrorMessage() {
        StringBuilder builder = new StringBuilder();

        builder.append(messageType.getMessage());

        if (this.ofStatic != null) {
            builder.append(" : " + ofStatic);
        }

        return builder.toString();
    }
}
