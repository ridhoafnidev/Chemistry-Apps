package com.example.appskimia.Activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.appskimia.R
import com.example.appskimia.Util.Tools
import kotlinx.android.synthetic.main.toolbar.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initToolbar()
        Tools.setSystemBarColor(this, R.color.green_500)
        Tools.setSystemBarLight(this)
    }

    private fun initToolbar() {
        toolbar.title = "Profil"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this, R.color.grey_100)
        Tools.setSystemBarLight(this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
