package br.com.leonardoferreira.future.controller.advice

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ErrorHandlerControllerAdvice {

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handleMissingKotlinParameterException(e: MissingKotlinParameterException): ResponseEntity<Map<String, List<String>>> {
        return ResponseEntity(
            mapOf(e.parameter.name!! to listOf("must not be null")),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<Map<String, List<String>>> {
        val bindingResult = e.bindingResult
        return buildBindResultResponseErrors(bindingResult)
    }

    private fun buildBindResultResponseErrors(bindingResult: BindingResult): ResponseEntity<Map<String, List<String>>> {
        val errors = bindingResult.allErrors.groupBy {
            it as FieldError
            it.field
        }.map {
            it.key to it.value.mapNotNull { it.defaultMessage }
        }.toMap()

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

}
