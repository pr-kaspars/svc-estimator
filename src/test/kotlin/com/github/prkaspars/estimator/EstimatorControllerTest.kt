package com.github.prkaspars.estimator

import com.github.prkaspars.estimator.service.EstimatorService
import com.github.prkaspars.estimator.service.Feasible
import com.github.prkaspars.estimator.service.Infeasible
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

@ExtendWith(MockitoExtension::class)
class EstimatorControllerTest {
    @Mock
    private lateinit var estimatorService: EstimatorService

    @InjectMocks
    private lateinit var estimatorController: EstimatorController

    @Test
    fun `estimate should throw bad request exception when limit is less than or equal to zero`() {
        val failure = assertThrows<ResponseStatusException> {
            estimatorController.estimate(EstimatorController.EstimateRequest(0, 1))
        }
        assertEquals(HttpStatus.BAD_REQUEST, failure.statusCode)
        assertEquals("Limit must be greater than 0", failure.reason)
    }

    @Test
    fun `estimate should throw bad request exception when length is less than or equal to zero`() {
        val failure = assertThrows<ResponseStatusException> {
            estimatorController.estimate(EstimatorController.EstimateRequest(1, -1))
        }
        assertEquals(HttpStatus.BAD_REQUEST, failure.statusCode)
        assertEquals("Length must be greater than 0", failure.reason)
    }

    @Test
    fun `estimate should should return negative duration and reason when survey is not feasible`() {
        `when`(estimatorService.estimate(10, 10))
            .thenReturn(Infeasible(0.1, 30))
        val response = estimatorController.estimate(EstimatorController.EstimateRequest(10, 10))
        assertEquals(
            EstimatorController.EstimateResponse(-1, "Cannot reliably complete the survey in 30 days or less"),
            response
        )
    }

    @Test
    fun `estimate should should return duration and no reason when survey is feasible`() {
        `when`(estimatorService.estimate(10, 10))
            .thenReturn(Feasible(0.9, 7))
        val response = estimatorController.estimate(EstimatorController.EstimateRequest(10, 10))
        assertEquals(
            EstimatorController.EstimateResponse(7, null),
            response
        )
    }
}
