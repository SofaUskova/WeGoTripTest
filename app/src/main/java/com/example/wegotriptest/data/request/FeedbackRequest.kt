package com.example.wegotriptest.data.request

import com.example.wegotriptest.presentation.models.Feedback
import kotlinx.serialization.Serializable

@Serializable
data class FeedbackRequest(
    val id: String,
    val tourRating: Int,
    val guideRating: Int,
    val infoRating: Int,
    val navRating: Int,
    val features: String? = null,
    val wishes: String? = null
)

fun toMapper(feedback: Feedback): FeedbackRequest {
    return with(feedback) {
        FeedbackRequest(
            id = id,
            tourRating = tourRating,
            guideRating = guideRating,
            infoRating = infoRating,
            navRating = navRating,
            features = features,
            wishes = wishes
        )
    }
}
