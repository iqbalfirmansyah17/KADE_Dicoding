package com.dicoding.iqbalfirmansyah.footballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val idLeague: Int?,
    val nameLeague: String?,
    val logoLeague: Int
) : Parcelable

