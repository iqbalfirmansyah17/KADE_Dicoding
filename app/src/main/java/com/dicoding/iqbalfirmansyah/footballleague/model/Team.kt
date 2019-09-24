package com.dicoding.iqbalfirmansyah.footballleague.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    var teamId: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strTeamBadge")
    var teamBadge: String? = null,

    @SerializedName("strTeamLogo")
    var teamLogo: String? = null,

    @SerializedName("intFormedYear")
    var teamFormedYear: Int? = null,

    @SerializedName("strKeyword")
    var teamKeyword: String? = null,

    @SerializedName("strWebsite")
    var teamWebsite: String? = null,

    @SerializedName("strStadiumThumb")
    var teamStadiumThumb: String? = null,

    @SerializedName("strStadiumLocation")
    var teamStadiumLocation: String? = null,

    @SerializedName("intStadiumCapacity")
    var teamStadiumCapacity: Int? = null,

    @SerializedName("strDescriptionEN")
    var teamDesriptionEN: String? = null
)