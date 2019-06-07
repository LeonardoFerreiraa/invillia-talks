package br.com.leonardoferreira.future.domain.response

data class ContactResponse(
    val id: Long,
    val name: String,
    val email: String,
    val createdAt: String,
    val updatedAt: String
)
