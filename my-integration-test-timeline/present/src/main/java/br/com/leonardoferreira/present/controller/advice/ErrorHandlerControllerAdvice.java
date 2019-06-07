package br.com.leonardoferreira.present.controller.advice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandlerControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        var bindingResult = e.getBindingResult();
        return buildBindResultResponseErrors(bindingResult);
    }

    private ResponseEntity<Map<String, List<String>>> buildBindResultResponseErrors(BindingResult bindingResult) {
        var result = bindingResult.getAllErrors()
                .stream()
                .map(it -> (FieldError) it)
                .collect(
                        Collectors.groupingBy(
                                FieldError::getField,
                                Collectors.mapping(
                                        DefaultMessageSourceResolvable::getDefaultMessage,
                                        Collectors.toList()
                                )
                        )
                );

        return ResponseEntity.badRequest().body(result);
    }

}
