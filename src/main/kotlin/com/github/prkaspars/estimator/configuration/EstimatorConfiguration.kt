package com.github.prkaspars.estimator.configuration

import com.github.prkaspars.estimator.client.cint.CintOrderingApi
import com.github.prkaspars.estimator.service.CachingFeasibilityProvider
import com.github.prkaspars.estimator.service.CintFeasibilityProvider
import com.github.prkaspars.estimator.service.FeasibilityProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class EstimatorConfiguration {
    @Bean
    fun feasibilityThreshold(): Double = 0.75

    @Bean
    fun estimateProvider(cintOrderingApi: CintOrderingApi): FeasibilityProvider {
        return CachingFeasibilityProvider(CintFeasibilityProvider(cintOrderingApi))
    }
}
