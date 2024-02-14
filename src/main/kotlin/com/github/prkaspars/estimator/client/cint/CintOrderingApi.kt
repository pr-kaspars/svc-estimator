package com.github.prkaspars.estimator.client.cint

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.service.annotation.HttpExchange
import org.springframework.web.service.annotation.PostExchange

@HttpExchange(
    url = "/ordering",
    accept = [MediaType.APPLICATION_JSON_VALUE],
    contentType = MediaType.APPLICATION_JSON_VALUE
)
interface CintOrderingApi {
    @PostExchange("/feasibilityestimates")
    fun feasibilityEstimates(@RequestBody request: FeasibilityEstimatesRequest): FeasibilityEstimatesResponse
}
