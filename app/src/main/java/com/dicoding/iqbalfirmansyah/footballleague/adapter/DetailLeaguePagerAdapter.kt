package com.dicoding.iqbalfirmansyah.footballleague.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.iqbalfirmansyah.footballleague.fragment.DetailLeagueFragment
import com.dicoding.iqbalfirmansyah.footballleague.fragment.TeamListFragment

class DetailLeaguePagerAdapter(
    fm: FragmentManager,
    private var tabCount: Int,
    private var id: Int,
    private var name: String
) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> return DetailLeagueFragment.newInstance(id)
            1 -> return TeamListFragment.newInstance(name)
            else -> TeamListFragment.newInstance(name)
        }
    }

    override fun getCount(): Int {
        return tabCount
    }
}