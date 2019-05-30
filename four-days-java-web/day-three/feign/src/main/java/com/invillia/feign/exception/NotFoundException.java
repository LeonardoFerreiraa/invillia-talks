package com.invillia.feign.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Uai! Buscou um trem que não existe")
public class NotFoundException extends RuntimeException {
}
