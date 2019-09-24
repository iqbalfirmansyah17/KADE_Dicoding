package com.dicoding.iqbalfirmansyah.footballleague.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dicoding.iqbalfirmansyah.footballleague.*
import com.dicoding.iqbalfirmansyah.footballleague.activity.DetailTeamActivity
import com.dicoding.iqbalfirmansyah.footballleague.adapter.FootballTeamAdapter
import com.dicoding.iqbalfirmansyah.footballleague.api.ApiRepository
import com.dicoding.iqbalfirmansyah.footballleague.model.DetailLeague
import com.dicoding.iqbalfirmansyah.footballleague.model.Team
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*


class TeamListFragment : Fragment(), MainView {
    private lateinit var listTeam: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: FootballTeamAdapter

    private var leagueName: String? = null

    companion object {
        fun newInstance(leagueName: String): TeamListFragment {
            val bundle = Bundle()
            bundle.putString("name", leagueName)

            val fragment = TeamListFragment()
            fragment.setArguments(bundle)
            return fragment
        }
    }

    private fun readBundle(bundle: Bundle?) {
        if (bundle != null) {
            leagueName = bundle.getString("name")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this@TeamListFragment, request, gson)
        presenter.getTeamList(leagueName)

        adapter = FootballTeamAdapter(teams) {
            toast("${it.teamName}")
            startActivity<DetailTeamActivity>(
                "name" to it.teamName,
                "logo" to it.teamBadge,
                "formedYear" to it.teamFormedYear
            )
        }
        listTeam.adapter = adapter
        swipeRefresh.onRefresh {
            presenter.getTeamList(leagueName)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        readBundle(getArguments())
        return UI {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.VERTICAL
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)

                swipeRefresh = swipeRefreshLayout {
                    setColorSchemeColors(
                        ContextCompat.getColor(context, R.color.colorAccent),
                        ContextCompat.getColor(context, android.R.color.holo_green_light),
                        ContextCompat.getColor(context, android.R.color.holo_orange_light),
                        ContextCompat.getColor(context, android.R.color.holo_red_light)
                    )

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)
                        listTeam = recyclerView {
                            layoutManager = GridLayoutManager(context, 2)
                        }.lparams(width = matchParent, height = wrapContent) {
                            below(R.id.tv_team_list)
                        }
                        progressBar = progressBar {
                        }.lparams {
                            centerInParent()
                        }
                    }
                }
            }
        }.view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamList(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showDetailLeague(data: DetailLeague) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}