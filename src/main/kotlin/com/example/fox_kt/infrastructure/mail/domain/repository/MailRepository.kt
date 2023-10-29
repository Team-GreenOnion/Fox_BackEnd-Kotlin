package com.example.fox_kt.infrastructure.mail.domain.repository

import com.example.fox_kt.infrastructure.mail.domain.Mail
import org.springframework.data.repository.CrudRepository

interface MailRepository : CrudRepository<Mail, String> {
}