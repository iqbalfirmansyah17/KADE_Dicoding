package com.dicoding.iqbalfirmansyah.footballleague

import com.dicoding.iqbalfirmansyah.footballleague.api.ApiRepository
import com.dicoding.iqbalfirmansyah.footballleague.api.TheSportDBApi
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getTeamList(leagueName: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getTeams(leagueName)),
                TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }

    fun getDetailLeague(leagueId: Int?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getLeagueById(leagueId)),
                DetailLeagueResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailLeague(data.leagues)
            }
        }
    }
}