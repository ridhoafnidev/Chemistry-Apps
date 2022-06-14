package com.example.appskimia.Activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appskimia.Adapter.AdapterSnapGeneric
import com.example.appskimia.BuildConfig
import com.example.appskimia.Data.DataGenerator
import com.example.appskimia.Helper.StartSnapHelper
import com.example.appskimia.R
import com.example.appskimia.Util.SharedPrefManager
import com.example.appskimia.Util.Tools
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var cvPetunjuk: CardView? = null
    private var cvKompetensi: CardView? = null
    private var cvMateri: CardView? = null
    private var cvEvaluasi: CardView? = null
    var sharedPrefManager: SharedPrefManager? = null
    var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initComponent()
        initAction()
    }

    private fun initAction() {
//        cvPetunjuk!!.setOnClickListener {
//            //showDialogAbout();
//            startActivity(Intent(applicationContext, KasusActivity::class.java))
//        }
        cvKompetensi!!.setOnClickListener { startActivity(Intent(applicationContext, KompetensiActivity::class.java)) }
        cvEvaluasi!!.setOnClickListener { startActivity(Intent(applicationContext, EvaluasiActivity::class.java)) }
        cvMateri!!.setOnClickListener { startActivity(Intent(applicationContext, MateriActivity::class.java)) }
        cv_profil.setOnClickListener { startActivity(Intent(applicationContext, ProfileActivity::class.java)) }
        cv_daftar_pustaka.setOnClickListener { startActivity(Intent(applicationContext, DaftarPustakaActivity::class.java)) }
    }

    private fun initComponent() {
        mContext = this
        //cvPetunjuk = findViewById(R.id.cv_petunjuk)
        cvKompetensi = findViewById(R.id.cv_kompetensi)
        cvMateri = findViewById(R.id.cv_materi)
        cvEvaluasi = findViewById(R.id.cv_evaluasi)
        val recyclerStart = findViewById<RecyclerView>(R.id.recyclerStart)
        recyclerStart.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        layoutManager.reverseLayout = true
        recyclerStart.layoutManager = layoutManager
        // generate data
        val items = DataGenerator.getImageDate(this)
        //set data and list adapter
        recyclerStart.adapter = AdapterSnapGeneric(this, items, R.layout.item_snap_basic)
        recyclerStart.onFlingListener = null
        StartSnapHelper().attachToRecyclerView(recyclerStart)
    }

    private fun showDialogAbout() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.dialog_about)
        dialog.setCancelable(true)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        (dialog.findViewById<View>(R.id.tv_version) as TextView).text = "Version " + BuildConfig.VERSION_NAME
        (dialog.findViewById(R.id.bt_getcode) as View).setOnClickListener {
            /*
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://codecanyon.net/user/dream_space/portfolio"));
                startActivity(i);
                */
        }
        (dialog.findViewById<View>(R.id.bt_close) as ImageButton).setOnClickListener { dialog.dismiss() }
        dialog.show()
        dialog.window!!.attributes = lp
    }

    private fun initToolbar() {
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        toolbar.title = "Pembelajaran Kimia"
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        Tools.setSystemBarColor(this, R.color.grey_100)
        Tools.setSystemBarLight(this)
    }
}