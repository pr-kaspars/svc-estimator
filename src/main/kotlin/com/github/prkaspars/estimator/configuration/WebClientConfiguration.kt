package com.github.prkaspars.estimator.configuration

import com.github.prkaspars.estimator.client.cint.CintOrderingApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
class WebClientConfiguration {
    @Bean
    fun cintWebClient(configuration: CintClientConfiguration): WebClient {
        return WebClient.builder()
            .baseUrl(configuration.baseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .defaultHeader("X-Api-Key", configuration.apiKey)
            .build()
    }

    @Bean
    fun cintOrderingApi(webClient: WebClient): CintOrderingApi {
        return HttpServiceProxyFactory.builderFor(WebClientAdapter.create(webClient))
            .build()
            .createClient(CintOrderingApi::class.java)
    }
}
