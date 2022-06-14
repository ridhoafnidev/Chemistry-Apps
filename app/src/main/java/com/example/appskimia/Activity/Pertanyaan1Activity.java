package com.example.appskimia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.appskimia.R;
import com.example.appskimia.Util.Tools;
import com.example.appskimia.mapper.MainActivityRadioButtonMapper;
import com.example.appskimia.ui.CustomRadioGroup;
import com.example.appskimia.ui.OnCustomRadioButtonListener;

public class Pertanyaan1Activity extends AppCompatActivity {
    public static final String IT_NAMA = "nama";
    private Button btnPrev, btnLanjut;
    private String mNama, mPertanyaan1;
    private int mJawaban1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan1);
        initToolbar();
        initComponent();
        initAction();
    }

    private void initComponent() {
        mNama = getIntent().getStringExtra(IT_NAMA);
        System.out.println("nama anda"+mNama);
        btnPrev =  findViewById(R.id.btn_prev);
        btnLanjut =  findViewById(R.id.btn_next);
        CustomRadioGroup.setOnClickListener((OnCustomRadioButtonListener) view -> {
            int viewId = view.getId();

            if (viewId == R.id.rb_jawaban_a){
                mJawaban1 = 0;
                showButtonTag(mJawaban1);
            }else if (viewId == R.id.rb_jawaban_b){
                mJawaban1 = 0;
                showButtonTag(mJawaban1);
            }else if (viewId == R.id.rb_jawaban_c){
                mJawaban1 = 10;
                showButtonTag(mJawaban1);
            }else if (viewId == R.id.rb_jawaban_d){
                mJawaban1 = 0;
                showButtonTag(mJawaban1);
            }else if (viewId == -1){
                mJawaban1 = 0;
                showButtonTag(mJawaban1);
            }else{
                mJawaban1 = 0;
                showButtonTag(mJawaban1);
            }
            /*
            switch (view.getId()) {
                case R.id.rb_jawaban_a:
                    mJawaban1 = 20;
                    showButtonTag(mJawaban1);
                    break;
                case R.id.rb_jawaban_b:
                    mJawaban1 = 0;
                    showButtonTag(mJawaban1);
                    break;
                case R.id.rb_jawaban_c:
                    mJawaban1 = 0;
                    showButtonTag(mJawaban1);
                    break;
                case R.id.rb_jawaban_d:
                    mJawaban1 = 0;
                    showButtonTag(mJawaban1);
                    break;
                default:
                    mJawaban1 = 0;
                    showButtonTag(mJawaban1);
                    break;
            }
            System.out.println("kecuali :" +0);

             */
        });
    }

    private void showButtonTag(int viewId) {
        /*
        MainActivityRadioButtonMapper mapper = new MainActivityRadioButtonMapper(this);
        String tag = mapper.mapToStrigFrom(viewId);
        */
        mPertanyaan1 = String.valueOf(viewId);
        //Toast.makeText(this, "R :"+mPertanyaan1+" : "+mNama, Toast.LENGTH_SHORT).show();
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

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Pertanyaan2Activity.class);
                intent.putExtra(Pertanyaan2Activity.IT_NAMA, mNama);
                intent.putExtra(Pertanyaan2Activity.IT_PERTANYAAN1, mPertanyaan1);
                startActivity(intent);
            }
        });

    }
}
