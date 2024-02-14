package com.github.prkaspars.estimator.client.cint

data class FeasibilityEstimatesRequest(
    val incidenceRate: Int,
    val lengthOfInterview: Int,
    val countryId: Int,
    val limit: Int,
    val fieldPeriod: Int,
    val quotaGroups: List<QuotaGroup>,
    val dedupeTags: List<Int>,
    val panelIds: List<Int>,
    val participationExclusionOverrides: List<Int>,
    val respondentIdentifiableInfo: Boolean,
    val webcamStudies: Boolean,
    val categories: List<Int>
) {
    data class QuotaGroup(
        val quotas: List<Quota>,
        val limit: Int
    )

    data class Quota(
        val limit: Int,
        val targetGroup: TargetGroup
    )

    data class TargetGroup(
        val maxAge: Int,
        val minAge: Int,
        val regionIds: List<Int>,
        val variableIds: List<Int>,
        val postalCodes: List<Int>,
        val panelIds: List<Int>
    )

    companion object {
        fun create(limit: Int, lengthOfInterview: Int, fieldPeriod: Int): FeasibilityEstimatesRequest {
            return FeasibilityEstimatesRequest(
                incidenceRate = 100,
                lengthOfInterview = lengthOfInterview,
                countryId = 1,
                limit = limit,
                fieldPeriod = fieldPeriod,
                quotaGroups = listOf(
                    QuotaGroup(
                        quotas = listOf(
                            Quota(
                                limit = limit,
                                targetGroup = TargetGroup(
                                    maxAge = 99,
                                    minAge = 18,
                                    regionIds = listOf(),
                                    variableIds = listOf(),
                                    postalCodes = listOf(),
                                    panelIds = listOf()
                                )
                            )
                        ),
                        limit = limit
                    )
                ),
                dedupeTags = listOf(),
                panelIds = listOf(),
                participationExclusionOverrides = listOf(),
                respondentIdentifiableInfo = false,
                webcamStudies = true,
                categories = listOf()
            )
        }
    }
}
