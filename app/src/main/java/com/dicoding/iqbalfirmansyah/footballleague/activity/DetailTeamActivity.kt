package com.dicoding.iqbalfirmansyah.footballleague.activity

import android.os.Bundle
import android.view.Gravity
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class DetailTeamActivity : AppCompatActivity() {

    private var name: String = ""
    private var logo: String = ""
    private var formedYear: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        name = intent.getStringExtra("name")
        logo = intent.getStringExtra("logo")
        formedYear = intent.getIntExtra("formedYear", 0)

        supportActionBar?.title = name
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        verticalLayout {
            linearLayout {
                cardView {
                    radius = dip(8).toFloat()
                    imageView {
                        Picasso.get().load(logo).into(this)
                    }
                }.lparams {
                    width = dip(200)
                    height = dip(200)
                    gravity = Gravity.CENTER
                    marginEnd = dip(10)
                }
                textView{
                    text = formedYear.toString()
                }
            }.lparams {
                horizontalMargin = dip(10)
                verticalMargin = dip(5)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}