package com.dicoding.iqbalfirmansyah.footballleague.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.dicoding.iqbalfirmansyah.footballleague.adapter.DetailLeaguePagerAdapter
import com.dicoding.iqbalfirmansyah.footballleague.R
import com.google.android.material.tabs.TabLayout
import org.jetbrains.anko.below
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.design.tabLayout
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.relativeLayout
import org.jetbrains.anko.support.v4.viewPager
import org.jetbrains.anko.wrapContent

class DetailLeagueActivity : AppCompatActivity() {

    private var leagueId: Int = 4328
    private var leagueName: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        leagueId = intent.getIntExtra("league_id", 4328)
        leagueName = intent.getStringExtra("league_name")
        supportActionBar?.title = leagueName
        coordinatorLayout {
            lparams(width = matchParent, height = matchParent)
            fitsSystemWindows = true

            relativeLayout {
                lparams(width = matchParent, height = matchParent)
                tabLayout {
                    id = R.id.tab_layout
                    setupWithViewPager(findViewById(R.id.view_pager))
                    addTab(newTab().setText("Detail League"))
                    addTab(newTab().setText("List Team"))
                    lparams(width = matchParent, height = wrapContent) {
                        tabGravity = TabLayout.GRAVITY_FILL
                        tabMode = TabLayout.MODE_FIXED
                    }
                }
                viewPager {
                    id = R.id.view_pager
                    adapter =
                        DetailLeaguePagerAdapter(
                            supportFragmentManager,
                            2,
                            leagueId, leagueName
                        )

                }.lparams(width = matchParent, height = matchParent){
                    below(R.id.tab_layout)
                }
            }
        }

        findViewById<ViewPager>(R.id.view_pager).addOnPageChangeListener(
            TabLayout.TabLayoutOnPageChangeListener(
                findViewById(R.id.tab_layout)
            )
        )

        findViewById<TabLayout>(R.id.tab_layout).addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                findViewById<ViewPager>(R.id.view_pager).currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }
}