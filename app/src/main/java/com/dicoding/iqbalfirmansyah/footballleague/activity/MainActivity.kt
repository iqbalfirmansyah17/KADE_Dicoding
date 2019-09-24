package com.dicoding.iqbalfirmansyah.footballleague.activity

import android.graphics.Typeface
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.iqbalfirmansyah.footballleague.R
import com.dicoding.iqbalfirmansyah.footballleague.adapter.FootballLeagueAdapter
import com.dicoding.iqbalfirmansyah.footballleague.model.League
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var listLeague: RecyclerView
    private var leagues: MutableList<League> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(width = matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            relativeLayout {
                lparams(width = matchParent, height = wrapContent)

                textView("List League") {
                    id = R.id.tv_league_list
                    textSize = 20f
                    typeface = Typeface.DEFAULT_BOLD
                }.lparams {
                    centerHorizontally()
                }
                listLeague = recyclerView {
                    layoutManager = LinearLayoutManager(context)
                }.lparams(width = matchParent, height = wrapContent) {
                    below(R.id.tv_league_list)
                }
            }
        }

        initData()
        listLeague.layoutManager = LinearLayoutManager(this)
        listLeague.adapter = FootballLeagueAdapter(leagues) {
            startActivity<DetailLeagueActivity>("league_name" to it.nameLeague, "league_id" to it.idLeague)
            //toast(it.idLeague.toString())
        }
    }

    private fun initData() {
        val id = resources.getIntArray(R.array.league_id)
        val name = resources.getStringArray(R.array.league_name)
        val image = resources.obtainTypedArray(R.array.league_logo)

        leagues.clear()
        for (i in id.indices) {
            leagues.add(
                League(
                    id[i],
                    name[i],
                    image.getResourceId(i, 0)
                )
            )
        }
        //Recycle the typed array
        image.recycle()
    }
}


