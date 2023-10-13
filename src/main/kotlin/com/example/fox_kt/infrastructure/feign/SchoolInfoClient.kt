package com.example.fox_kt.infrastructure.feign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "client", url = "https://open.neis.go.kr/hub/schoolInfo", configuration = [Config::class])
interface SchoolInfoClient {
    @GetMapping(produces = ["application/json"])
    fun getSchoolInfo(
            @RequestParam(name = "Type") type: String,
            @RequestParam(name = "pIndex")pIndex: Int,
            @RequestParam(name = "pSize") pSize: Int,
            @RequestParam(name = "SCHUL_KND_SC_NM") schoolName: String,
            @RequestParam(name = "KEY") key: String,
    ): SchoolInfoResponse
}
