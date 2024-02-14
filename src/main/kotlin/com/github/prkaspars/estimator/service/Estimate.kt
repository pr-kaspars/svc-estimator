package com.github.prkaspars.estimator.service

sealed interface Estimate {
    val feasibility: Double
    val duration: Int
}

data class Feasible(
    override val feasibility: Double,
    override val duration: Int
) : Estimate

data class Infeasible(
    override val feasibility: Double,
    override val duration: Int
) : Estimate
