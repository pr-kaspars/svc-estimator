package com.github.prkaspars.estimator.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class CachingFeasibilityProviderTest {
    @Mock
    private lateinit var feasibilityProvider: FeasibilityProvider

    @InjectMocks
    private lateinit var cachingFeasibilityProvider: CachingFeasibilityProvider

    @Test
    fun `getFeasibility should return feasibility from cache`() {
        `when`(feasibilityProvider.getFeasibility(10, 10, 7))
            .thenReturn(0.99)
        assertEquals(0.99, cachingFeasibilityProvider.getFeasibility(10, 10, 7))
        assertEquals(0.99, cachingFeasibilityProvider.getFeasibility(10, 10, 7))
        assertEquals(0.99, cachingFeasibilityProvider.getFeasibility(10, 10, 7))
        verify(feasibilityProvider, times(1)).getFeasibility(10, 10, 7)
    }
}
