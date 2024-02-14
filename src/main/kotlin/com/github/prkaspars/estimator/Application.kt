package com.github.prkaspars.estimator

import com.github.prkaspars.estimator.configuration.CintClientConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(CintClientConfiguration::class)
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
