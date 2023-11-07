package com.example.fox_kt.infrastructure.mail.domain.repository

import com.example.fox_kt.infrastructure.mail.domain.Mail
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MailRepository : JpaRepository<Mail, String>