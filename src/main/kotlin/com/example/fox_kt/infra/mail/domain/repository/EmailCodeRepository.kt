package com.example.fox_kt.infra.mail.domain.repository

import com.example.fox_kt.infra.mail.domain.MailCode
import org.springframework.data.repository.CrudRepository

interface EmailCodeRepository : CrudRepository<MailCode, String> {
}