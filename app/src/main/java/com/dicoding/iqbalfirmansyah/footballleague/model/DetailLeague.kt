package com.dicoding.iqbalfirmansyah.footballleague.model

import com.google.gson.annotations.SerializedName

data class DetailLeague(
    @SerializedName("idLeague")
    var leagueId: String? = null,

    @SerializedName("strLeague")
    var leagueName: String? = null,

    @SerializedName("strDescriptionEN")
    var leagueDescription: String? = null
)