package com.github.prkaspars.estimator.service

import org.springframework.stereotype.Service

@Service
class EstimatorService(private val feasibilityProvider: FeasibilityProvider) {
    private val feasibilityThreshold: Double = 0.75

    fun estimate(limit: Int, lengthOfInterview: Int): Estimate {
        val feasibilityWeek = feasibilityProvider.getFeasibility(limit, lengthOfInterview, 7)
        return if (feasibilityWeek > feasibilityThreshold) {
            val estimateDay = feasibilityProvider.getFeasibility(limit, lengthOfInterview, 1)
            if (estimateDay > feasibilityThreshold) {
                Feasible(estimateDay, 1)
            } else {
                Feasible(feasibilityWeek, 7)
            }
        } else {
            val feasibilityMonth = feasibilityProvider.getFeasibility(limit, lengthOfInterview, 30)
            if (feasibilityMonth > feasibilityThreshold) {
                Feasible(feasibilityMonth, 30)
            } else {
                Infeasible(feasibilityMonth, 30)
            }
        }
    }
}
