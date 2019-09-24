package com.dicoding.iqbalfirmansyah.footballleague.adapter

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.iqbalfirmansyah.footballleague.R
import com.dicoding.iqbalfirmansyah.footballleague.model.League
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class FootballLeagueAdapter(
    private val leagues: List<League>,
    private val listener: (League) -> Unit
) :
    RecyclerView.Adapter<LeagueViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        return LeagueViewHolder(
            LeagueUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bindItem(leagues[position], listener)
    }
}

class LeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val leagueId: TextView = view.findViewById(R.id.league_id)
    private val leagueName: TextView = view.findViewById(R.id.league_name)
    private val leagueLogo: ImageView = view.findViewById(R.id.league_logo)
    fun bindItem(leagues: League, listener: (League) -> Unit) {
        leagueId.text = leagues.idLeague.toString()
        leagueName.text = leagues.nameLeague
        Picasso.get().load(leagues.logoLeague).fit().into(leagueLogo)
        itemView.setOnClickListener {
            listener(leagues)
        }
    }
}

class LeagueUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            cardView {
                lparams(width = matchParent, height = wrapContent) {
                    bottomMargin = dip(5)
                }
                radius = dip(8).toFloat()
                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    padding = dip(15)
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = R.id.league_id
                        textSize = 16f
                    }.lparams {
                        margin = dip(5)
                    }
                    textView {
                        id = R.id.league_name
                        textSize = 16f
                    }.lparams {
                        margin = dip(5)
                    }
                }
                imageView {
                    id = R.id.league_logo
                    backgroundColor = Color.GRAY
                }.lparams(width = dip(120), height = dip(80)) {
                    margin = dip(5)
                    gravity = Gravity.END
                }
            }
        }
    }
}