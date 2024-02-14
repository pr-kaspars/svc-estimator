package com.github.prkaspars.estimator.service

interface FeasibilityProvider {
    fun getFeasibility(limit: Int, length: Int, duration: Int): Double
}
