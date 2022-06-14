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

public class Pertanyaan10Activity extends AppCompatActivity {

    public static final String IT_NAMA = "nama";
    public static final String IT_PERTANYAAN1 = "pertanyaan1";
    public static final String IT_PERTANYAAN2 = "pertanyaan2";
    public static final String IT_PERTANYAAN3 = "pertanyaan3";
    public static final String IT_PERTANYAAN4 = "pertanyaan4";
    public static final String IT_PERTANYAAN5 = "pertanyaan5";
    public static final String IT_PERTANYAAN6 = "pertanyaan6";
    public static final String IT_PERTANYAAN7 = "pertanyaan7";
    public static final String IT_PERTANYAAN8 = "pertanyaan8";
    public static final String IT_PERTANYAAN9 = "pertanyaan9";
    public static final String IT_PERTANYAAN10 = "pertanyaan10";
    private String mNama, mPertanyaan1, mPertanyaan2, mPertanyaan3, mPertanyaan4, mPertanyaan5, mPertanyaan6, mPertanyaan7, mPertanyaan8, mPertanyaan9;
    private int mJawaban10;
    private Button btnPrev, btnLanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan10);
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
        mPertanyaan6 = getIntent().getStringExtra(IT_PERTANYAAN6);
        mPertanyaan7 = getIntent().getStringExtra(IT_PERTANYAAN7);
        mPertanyaan8 = getIntent().getStringExtra(IT_PERTANYAAN8);
        mPertanyaan9 = getIntent().getStringExtra(IT_PERTANYAAN9);
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
                    mJawaban10 = 0;
                    showButtonTag(mJawaban10);
                    break;
                case R.id.rb_jawaban_b:
                    mJawaban10 = 0;
                    showButtonTag(mJawaban10);
                    break;
                case R.id.rb_jawaban_c:
                    mJawaban10 = 10;
                    showButtonTag(mJawaban10);
                    break;
                case R.id.rb_jawaban_d:
                    mJawaban10 = 0;
                    showButtonTag(mJawaban10);
                    break;
                default:
                    mJawaban10 = 0;
                    showButtonTag(mJawaban10);
                    break;
            }
        });
    }

    private void showButtonTag(int viewId) {
        mPertanyaan9 = String.valueOf(viewId);
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

        btnPrev.setOnClickListener(view -> {
            onBackPressed();
            finish();
        });

        btnLanjut.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), HasilActivity.class);
            intent.putExtra(HasilActivity.IT_NAMA, mNama);
            intent.putExtra(HasilActivity.IT_PERTANYAAN1, mPertanyaan1);
            intent.putExtra(HasilActivity.IT_PERTANYAAN2, mPertanyaan2);
            intent.putExtra(HasilActivity.IT_PERTANYAAN3, mPertanyaan3);
            intent.putExtra(HasilActivity.IT_PERTANYAAN4, mPertanyaan4);
            intent.putExtra(HasilActivity.IT_PERTANYAAN5, mPertanyaan5);
            intent.putExtra(HasilActivity.IT_PERTANYAAN6, mPertanyaan6);
            intent.putExtra(HasilActivity.IT_PERTANYAAN7, mPertanyaan7);
            intent.putExtra(HasilActivity.IT_PERTANYAAN8, mPertanyaan8);
            intent.putExtra(HasilActivity.IT_PERTANYAAN9, mPertanyaan9);
            intent.putExtra(HasilActivity.IT_PERTANYAAN10, mPertanyaan9);
            startActivity(intent);
        });

    }

}

