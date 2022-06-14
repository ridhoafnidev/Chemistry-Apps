package com.example.appskimia.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.appskimia.R
import com.example.appskimia.Util.Tools
import kotlinx.android.synthetic.main.toolbar.*

class DaftarPustakaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daftar_pustaka)
        initToolbar()
        Tools.setSystemBarColor(this, R.color.green_500)
        Tools.setSystemBarLight(this)
    }

    private fun initToolbar() {
        toolbar.title = "Daftar Pustaka"
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
