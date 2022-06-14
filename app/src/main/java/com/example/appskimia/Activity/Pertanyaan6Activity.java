package com.example.appskimia.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appskimia.R;
import com.example.appskimia.Util.Tools;
import com.example.appskimia.ui.CustomRadioGroup;
import com.example.appskimia.ui.OnCustomRadioButtonListener;

public class Pertanyaan6Activity extends AppCompatActivity {

    public static final String IT_NAMA = "nama";
    public static final String IT_PERTANYAAN1 = "pertanyaan1";
    public static final String IT_PERTANYAAN2 = "pertanyaan2";
    public static final String IT_PERTANYAAN3 = "pertanyaan3";
    public static final String IT_PERTANYAAN4 = "pertanyaan4";
    public static final String IT_PERTANYAAN5 = "pertanyaan5";

    private String mNama, mPertanyaan1, mPertanyaan2, mPertanyaan3, mPertanyaan4, mPertanyaan5, mPertanyaan6;
    private int mJawaban6;
    private Button btnPrev, btnLanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan6);
        initComponent();
        initIntent();
        initAction();
        initToolbar();
    }

    private void initIntent() {
        mNama = getIntent().getStringExtra(IT_NAMA);
        mPertanyaan1 = getIntent().getStringExtra(IT_PERTANYAAN1);
        mPertanyaan2 = getIntent().getStringExtra(IT_PERTANYAAN2);
        mPertanyaan3 = getIntent().getStringExtra(IT_PERTANYAAN3);
        mPertanyaan4 = getIntent().getStringExtra(IT_PERTANYAAN4);
        mPertanyaan5 = getIntent().getStringExtra(IT_PERTANYAAN5);
        System.out.println("Nama : "+mNama);
        System.out.println("Per 1: "+mPertanyaan1);
        System.out.println("Per 2: "+mPertanyaan2);
        System.out.println("Per 3: "+mPertanyaan3);
        System.out.println("Per 4: "+mPertanyaan4);
    }

    private void initComponent() {
        mNama = getIntent().getStringExtra(IT_NAMA);
        System.out.println("nama anda"+mNama);
        btnPrev =  findViewById(R.id.btn_prev);
        btnLanjut =  findViewById(R.id.btn_next);
        CustomRadioGroup.setOnClickListener((OnCustomRadioButtonListener) view -> {
            switch (view.getId()) {
                case R.id.rb_jawaban_a:
                    mJawaban6 = 10;
                    showButtonTag(mJawaban6);
                    break;
                case R.id.rb_jawaban_b:
                    mJawaban6 = 0;
                    showButtonTag(mJawaban6);
                    break;
                case R.id.rb_jawaban_c:
                    mJawaban6 = 0;
                    showButtonTag(mJawaban6);
                    break;
                case R.id.rb_jawaban_d:
                    mJawaban6 = 0;
                    showButtonTag(mJawaban6);
                    break;
                default:
                    mJawaban6 = 0;
                    showButtonTag(mJawaban6);
                    break;
            }
        });
    }

    private void showButtonTag(int viewId) {
        /*
        MainActivityRadioButtonMapper mapper = new MainActivityRadioButtonMapper(this);
        String tag = mapper.mapToStrigFrom(viewId);
        */
        mPertanyaan6 = String.valueOf(viewId);
        // Toast.makeText(this, "R :"+mPertanyaan4+" : "+mNama, Toast.LENGTH_SHORT).show();
    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Evaluasi");
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
    private void initAction() {

        btnPrev.setOnClickListener( view -> {
            onBackPressed();
            finish();
        });

        btnLanjut.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), Pertanyaan7Activity.class);
            intent.putExtra(Pertanyaan7Activity.IT_NAMA, mNama);
            intent.putExtra(Pertanyaan7Activity.IT_PERTANYAAN1, mPertanyaan1);
            intent.putExtra(Pertanyaan7Activity.IT_PERTANYAAN2, mPertanyaan2);
            intent.putExtra(Pertanyaan7Activity.IT_PERTANYAAN3, mPertanyaan3);
            intent.putExtra(Pertanyaan7Activity.IT_PERTANYAAN4, mPertanyaan4);
            intent.putExtra(Pertanyaan7Activity.IT_PERTANYAAN5, mPertanyaan5);
            intent.putExtra(Pertanyaan7Activity.IT_PERTANYAAN6, mPertanyaan6);
            startActivity(intent);
        });

    }

}

