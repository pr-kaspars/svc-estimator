package com.github.prkaspars.estimator.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class EstimatorServiceTest {
    @Mock
    private lateinit var feasibilityProvider: FeasibilityProvider

    @InjectMocks
    private lateinit var estimatorService: EstimatorService

    @Test
    fun `estimate should return Infeasible when 30 day survey feasibility does not reach threshold`() {
        `when`(feasibilityProvider.getFeasibility(10, 10, 7))
            .thenReturn(0.5)
        `when`(feasibilityProvider.getFeasibility(10, 10, 30))
            .thenReturn(0.6)
        val estimate = estimatorService.estimate(10, 10)
        assertEquals(Infeasible(0.6, 30), estimate)
    }

    @Test
    fun `estimate should return Feasible when 30 day survey feasibility reaches threshold`() {
        `when`(feasibilityProvider.getFeasibility(10, 10, 7))
            .thenReturn(0.5)
        `when`(feasibilityProvider.getFeasibility(10, 10, 30))
            .thenReturn(0.76)
        val estimate = estimatorService.estimate(10, 10)
        assertEquals(Feasible(0.76, 30), estimate)
    }

    @Test
    fun `estimate should return Feasible when 7 day survey feasibility reaches threshold`() {
        `when`(feasibilityProvider.getFeasibility(10, 10, 7))
            .thenReturn(0.8)
        `when`(feasibilityProvider.getFeasibility(10, 10, 1))
            .thenReturn(0.5)
        val estimate = estimatorService.estimate(10, 10)
        assertEquals(Feasible(0.8, 7), estimate)
    }

    @Test
    fun `estimate should return Feasible when 1 day survey feasibility reaches threshold`() {
        `when`(feasibilityProvider.getFeasibility(10, 10, 7))
            .thenReturn(0.99)
        `when`(feasibilityProvider.getFeasibility(10, 10, 1))
            .thenReturn(0.9)
        val estimate = estimatorService.estimate(10, 10)
        assertEquals(Feasible(0.9, 1), estimate)
    }
}
