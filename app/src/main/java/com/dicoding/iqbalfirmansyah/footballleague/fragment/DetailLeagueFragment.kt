package com.dicoding.iqbalfirmansyah.footballleague.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.dicoding.iqbalfirmansyah.footballleague.*
import com.dicoding.iqbalfirmansyah.footballleague.adapter.DetailLeagueAdapter
import com.dicoding.iqbalfirmansyah.footballleague.api.ApiRepository
import com.dicoding.iqbalfirmansyah.footballleague.model.DetailLeague
import com.dicoding.iqbalfirmansyah.footballleague.model.Team
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.UI
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.toast


class DetailLeagueFragment : Fragment(), MainView {
    private var leagueId: Int? = null
    private lateinit var presenter: MainPresenter
    private lateinit var progressBar: ProgressBar

    private val detailLeague: DetailLeague = DetailLeague()

    companion object {
        fun newInstance(leagueId: Int): DetailLeagueFragment {
            val bundle = Bundle()
            bundle.putInt("id", leagueId)

            val fragment = DetailLeagueFragment()
            fragment.setArguments(bundle)
            return fragment
        }
    }

    private fun readBundle(bundle: Bundle?) {
        if (bundle != null) {
            leagueId = bundle.getInt("id")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        readBundle(getArguments())
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this@DetailLeagueFragment, request, gson)
        presenter.getDetailLeague(leagueId)
        val leagueName: TextView = find(R.id.league_name_detail)
        leagueName.text = detailLeague.leagueName
        val leagueDescription: TextView = find(R.id.league_description_detail)
        leagueDescription.text = detailLeague.leagueDescription
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return UI {
                        relativeLayout {
                lparams(width = matchParent, height = wrapContent)
                topPadding = dip(16)
                leftPadding = dip(16)
                rightPadding = dip(16)
                textView {
                    id = R.id.league_name_detail
                    textSize = 20f
                    typeface = Typeface.DEFAULT_BOLD
                }
                textView {
                    id = R.id.league_description_detail
                    textSize = 20f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    below(R.id.league_name_detail)
                }
                progressBar = progressBar {
                }.lparams {
                    centerInParent()
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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showDetailLeague(data: DetailLeague) {
    }
}

