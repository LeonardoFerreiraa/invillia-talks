package br.com.leonardoferreira.future.domain.request

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class ContactRequest(

    @field:NotBlank
    val name: String,

    @field:Email
    @field:NotBlank
    val email: String

)
