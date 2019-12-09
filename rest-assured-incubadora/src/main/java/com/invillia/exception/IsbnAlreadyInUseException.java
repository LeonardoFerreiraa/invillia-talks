package com.invillia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "ISBN already in use")
public class IsbnAlreadyInUseException extends RuntimeException {
}
