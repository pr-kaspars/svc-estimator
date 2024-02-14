package com.github.prkaspars.estimator.service

import com.github.prkaspars.estimator.client.cint.CintOrderingApi
import com.github.prkaspars.estimator.client.cint.FeasibilityEstimatesRequest

class CintFeasibilityProvider(private val api: CintOrderingApi) : FeasibilityProvider {
    override fun getFeasibility(limit: Int, length: Int, duration: Int): Double {
        val request = FeasibilityEstimatesRequest.create(limit, length, duration)
        val response = api.feasibilityEstimates(request)
        return response.feasibility
    }
}
