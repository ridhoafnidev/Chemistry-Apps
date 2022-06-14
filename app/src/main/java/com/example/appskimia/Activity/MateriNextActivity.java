package com.example.appskimia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.appskimia.R;
import com.example.appskimia.Util.Tools;

public class MateriNextActivity extends AppCompatActivity {

    private ImageButton ibMateriPrev;
    private VideoView vvTugas1;
    private VideoView vvTugas2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_next);
        initComponent();
        initToolbar();
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Materi");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.green_500);
        Tools.setSystemBarLight(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initComponent(){
        vvTugas1 = findViewById(R.id.vv_tugas1);
        vvTugas2 = findViewById(R.id.vv_tugas2);
        vvTugas1.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.tugas1));
        vvTugas2.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.tugas2));
        // digunakan untuk mengidentifikasi resource seperti lokasi video
        vvTugas1.setMediaController(new MediaController(this));
        vvTugas2.setMediaController(new MediaController(this));
        // menampilkan media controller video
        vvTugas1.stopPlayback();
        vvTugas2.stopPlayback();
        ibMateriPrev = findViewById(R.id.ib_prev);
        ibMateriPrev.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           onBackPressed();
        }
    });
    }
}
