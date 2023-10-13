package com.example.fox_kt.infrastructure.feign

import com.example.fox_kt.domain.school.domain.School
import com.example.fox_kt.domain.school.domain.repository.SchoolRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SchoolFeignService(
    private val schoolRepository: SchoolRepository,
    private val schoolInfoClient: SchoolInfoClient
) {

    @Transactional
    fun fetchAndSaveSchoolInfoToDB() {
            val schoolInfoResponses = schoolInfoClient.getSchoolInfo("json", 1, 100, "고등학교", "c286db4930774debacc664b905a7524a")

            val schoolInfoEntity = School(
                schulNm = schoolInfoResponses.schulNm,
                atptOfcdcScNm = schoolInfoResponses.atptOfcdcScNm,
                hmpgAdres = schoolInfoResponses.hmpgAdres,
                coeduScNm = schoolInfoResponses.coeduScNm,
                lctnScNm = schoolInfoResponses.lctnScNm,
                hsScNm = schoolInfoResponses.hsScNm,
                orgRdnma = schoolInfoResponses.orgRdnma,
                orgTelno = schoolInfoResponses.orgTelno
            )
            schoolRepository.save(schoolInfoEntity)

    }
}