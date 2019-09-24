package com.dicoding.iqbalfirmansyah.footballleague.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.iqbalfirmansyah.footballleague.R
import com.dicoding.iqbalfirmansyah.footballleague.model.DetailLeague
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView


class DetailLeagueAdapter(
    private val detailLeagues: List<DetailLeague>,
    private val listener: (DetailLeague) -> Unit
) :
    RecyclerView.Adapter<DetailLeagueViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailLeagueViewHolder {
        return DetailLeagueViewHolder(
            DetailLeagueUI().createView(
                AnkoContext.create(parent.context, parent)
            )
        )
    }

    override fun getItemCount(): Int = detailLeagues.size

    override fun onBindViewHolder(holder: DetailLeagueViewHolder, position: Int) {
        holder.bindItem(detailLeagues[position], listener)
    }
}

class DetailLeagueViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val leagueId: TextView = view.findViewById(R.id.league_id_detail)
    private val leagueName: TextView = view.findViewById(R.id.league_name_detail)
    private val leagueDescription: TextView = view.findViewById(R.id.league_description_detail)
    fun bindItem(detailLeagues: DetailLeague, listener: (DetailLeague) -> Unit) {
        leagueId.text = detailLeagues.leagueId.toString()
        leagueName.text = detailLeagues.leagueName
        leagueDescription.text = detailLeagues.leagueDescription
        itemView.setOnClickListener {
            listener(detailLeagues)
        }
    }
}

class DetailLeagueUI : AnkoComponent<ViewGroup> {
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
                        id = R.id.league_id_detail
                        textSize = 16f
                    }.lparams {
                        margin = dip(5)
                    }
                    textView {
                        id = R.id.league_name_detail
                        textSize = 16f
                    }.lparams {
                        margin = dip(5)
                    }
                    textView {
                        id = R.id.league_description_detail
                        textSize = 16f
                    }.lparams {
                        margin = dip(5)
                    }
                }
            }
        }
    }
}