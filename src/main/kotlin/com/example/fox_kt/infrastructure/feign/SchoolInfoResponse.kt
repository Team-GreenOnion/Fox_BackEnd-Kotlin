package com.example.fox_kt.infrastructure.feign

import com.fasterxml.jackson.annotation.JsonProperty

data class SchoolInfoResponse(
    @JsonProperty("ATPT_OFCDC_SC_NM") val atptOfcdcScNm: String,
    @JsonProperty("SCHUL_NM")val schulNm: String,
    @JsonProperty("LCTN_SC_NM")val lctnScNm: String,
    @JsonProperty("ORG_RDNDA")val orgRdnma: String,
    @JsonProperty("ORG_TELNO")val orgTelno: String,
    @JsonProperty("HMPG_ADRES")val hmpgAdres: String,
    @JsonProperty("COEDU_SC_NM")val coeduScNm: String,
    @JsonProperty("HS_SC_NM")val hsScNm: String
)
