package com.github.prkaspars.estimator

import com.github.prkaspars.estimator.service.EstimatorService
import com.github.prkaspars.estimator.service.Feasible
import com.github.prkaspars.estimator.service.Infeasible
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class EstimatorController(private val estimatorService: EstimatorService) {

    @PostMapping(path = ["/estimate"])
    fun estimate(@RequestBody request: EstimateRequest): EstimateResponse {
        if (request.limit <= 0) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Limit must be greater than 0")
        }
        if (request.length <= 0) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Length must be greater than 0")
        }

        return when (val estimate = estimatorService.estimate(request.limit, request.length)) {
            is Feasible -> EstimateResponse(estimate.duration)
            is Infeasible -> EstimateResponse(-1, "Cannot reliably complete the survey in ${estimate.duration} days or less")
        }
    }

    data class EstimateRequest(
        val limit: Int,
        val length: Int
    )

    data class EstimateResponse(
        val duration: Int,
        val error: String? = null
    )
}
