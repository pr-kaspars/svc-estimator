package com.github.prkaspars.estimator.service

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.LoadingCache
import java.util.concurrent.TimeUnit

class CachingFeasibilityProvider(private val feasibilityProvider: FeasibilityProvider) : FeasibilityProvider {
    private val cache: LoadingCache<Key, Double> = Caffeine.newBuilder()
        .expireAfterWrite(1, TimeUnit.HOURS)
        .maximumSize(100)
        .build(this::fetch)

    override fun getFeasibility(limit: Int, length: Int, duration: Int): Double {
        return cache.get(Key(limit, length, duration))
    }

    private fun fetch(key: Key): Double {
        return feasibilityProvider.getFeasibility(key.limit, key.length, key.duration)
    }

    private data class Key(val limit: Int, val length: Int, val duration: Int)
}
