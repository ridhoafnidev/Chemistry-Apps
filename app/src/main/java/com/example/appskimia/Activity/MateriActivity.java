package com.example.appskimia.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appskimia.R;
import com.example.appskimia.Util.Tools;
import com.google.android.material.textfield.TextInputLayout;


public class MateriActivity extends AppCompatActivity {

    private ImageButton ibMateriNext, ibMateriPrev;
    private VideoView vvTugas1;
    private VideoView vvTugas2;
    private VideoView vvTugas3;
    private VideoView vvTugas4;
    private TextInputLayout edt1, edt2, edt3;
    private ImageView btn1, btn2, btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);

        initComponent();
        initAction();
        initToolbar();
        Tools.setSystemBarColor(this, R.color.green_500);
        Tools.setSystemBarLight(this);
    }

    private void initAction() {
//        ibMateriNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MateriActivity.this, MateriNextActivity.class));
//            }
//        });
//
//        ibMateriPrev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
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
        vvTugas1 = findViewById(R.id.cv_video1);
        vvTugas2 = findViewById(R.id.cv_video2);
        vvTugas3 = findViewById(R.id.cv_video3);
        vvTugas4 = findViewById(R.id.cv_video4);

        edt1 = findViewById(R.id.edt_1);
        edt2 = findViewById(R.id.edt_2);
        edt3 = findViewById(R.id.edt_3);

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);

        vvTugas1.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.vidio1));
        vvTugas2.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.vidio2));
        vvTugas3.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.vidio3));
        vvTugas4.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.vidio4));
        // digunakan untuk mengidentifikasi resource seperti lokasi video
        vvTugas1.setMediaController(new MediaController(this));
        vvTugas2.setMediaController(new MediaController(this));
        vvTugas3.setMediaController(new MediaController(this));
        vvTugas4.setMediaController(new MediaController(this));
        // menampilkan media controller video
        vvTugas1.stopPlayback();
        vvTugas2.stopPlayback();
        vvTugas3.stopPlayback();
        vvTugas4.stopPlayback();

        btn1.setOnClickListener(v -> edt1.setVisibility(View.VISIBLE) );
        btn2.setOnClickListener(v -> edt2.setVisibility(View.VISIBLE) );
        btn3.setOnClickListener(v -> edt3.setVisibility(View.VISIBLE) );
    }
}
