package com.example.appskimia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appskimia.R;
import com.example.appskimia.Util.Tools;
import com.example.appskimia.ui.CustomRadioGroup;
import com.example.appskimia.ui.OnCustomRadioButtonListener;

public class HasilActivity extends AppCompatActivity {
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

    private String mNama, mPertanyaan1, mPertanyaan2, mPertanyaan3, mPertanyaan4, mPertanyaan5, mPertanyaan6, mPertanyaan7,  mPertanyaan8,  mPertanyaan9, mPertanyaan10;
    private TextView tvNama, tvSkor;
    int hasil;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        initIntent();
        initComponent();
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
        mPertanyaan10 = getIntent().getStringExtra(IT_PERTANYAAN10);

        int jaw1, jaw2, jaw3, jaw4, jaw5, jaw6, jaw7, jaw8, jaw9, jaw10;

        jaw1 = Integer.parseInt(mPertanyaan1);
        jaw2 = Integer.parseInt(mPertanyaan2);
        jaw3 = Integer.parseInt(mPertanyaan3);
        jaw4 = Integer.parseInt(mPertanyaan4);
        jaw5 = Integer.parseInt(mPertanyaan5);
        jaw6 = Integer.parseInt(mPertanyaan6);
        jaw7 = Integer.parseInt(mPertanyaan7);
        jaw8 = Integer.parseInt(mPertanyaan8);
        jaw9 = Integer.parseInt(mPertanyaan9);
        jaw10 = Integer.parseInt(mPertanyaan10);

        hasil = jaw1 + jaw2 + jaw3 + jaw4 + jaw5 + jaw6 + jaw7 + jaw8 + jaw9 + jaw10;

        System.out.println("Hasil : "+hasil);

    }

    private void initComponent() {
        tvNama = findViewById(R.id.tv_nama);
        tvSkor = findViewById(R.id.tv_skor);

        tvNama.setText(mNama);
        tvSkor.setText(String.valueOf(hasil));

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
            startActivity(new Intent(HasilActivity.this, MainActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

}
