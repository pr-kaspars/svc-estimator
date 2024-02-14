package com.github.prkaspars.estimator.configuration

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "clients.cint")
data class CintClientConfiguration(
    val baseUrl: String,
    val apiKey: String
)
