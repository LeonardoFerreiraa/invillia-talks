package br.com.leonardoferreira.future.repository

import br.com.leonardoferreira.future.domain.Contact
import org.springframework.data.jpa.repository.JpaRepository

interface ContactRepository : JpaRepository<Contact, Long>
