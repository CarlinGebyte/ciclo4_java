package com.reto4.Exceptions;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseCustomExceptionUser {
    private String message;
    private int errorCode;
}
