package com.example.wegotriptest.presentation.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Feedback(
    val id: String,
    val tourRating: Int,
    val guideRating: Int,
    val infoRating: Int,
    val navRating: Int,
    val features: String? = null,
    val wishes: String? = null
) : Parcelable
