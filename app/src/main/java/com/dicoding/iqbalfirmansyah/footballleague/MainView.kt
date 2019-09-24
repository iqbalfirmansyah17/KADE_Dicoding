package com.dicoding.iqbalfirmansyah.footballleague

import com.dicoding.iqbalfirmansyah.footballleague.model.DetailLeague
import com.dicoding.iqbalfirmansyah.footballleague.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
    fun showDetailLeague(data: DetailLeague)
}