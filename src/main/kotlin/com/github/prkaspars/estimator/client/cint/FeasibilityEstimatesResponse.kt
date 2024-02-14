package com.github.prkaspars.estimator.client.cint

data class FeasibilityEstimatesResponse(
    val incidenceRate: Int,
    val lengthOfInterview: Int,
    val countryId: Int,
    val limit: Int,
    val fieldPeriod: Int,
    val quotaGroups: List<QuotaGroup>,
    val dedupeTags: List<Int>,
    val panelIds: List<Int>,
    val feasibility: Double,
    val feasibilityCount: Int,
    val participationExclusionOverrides: List<Int>,
    val respondentIdentifiableInfo: Boolean,
    val webcamStudies: Boolean,
    val categories: List<Int>,
    val errors: List<Int>
) {
    data class QuotaGroup(
        val quotas: List<Quota>,
        val limit: Int,
        val errors: List<Int>
    )

    data class Quota(
        val limit: Int,
        val targetGroup: TargetGroup,
        val feasibilityCount: Int,
        val errors: List<Int>
    )

    data class TargetGroup(
        val maxAge: Int,
        val minAge: Int,
        val regionIds: List<Int>,
        val variableIds: List<Int>,
        val errors: List<Int>,
        val postalCodes: List<Int>,
        val panelIds: List<Int>
    )
}
