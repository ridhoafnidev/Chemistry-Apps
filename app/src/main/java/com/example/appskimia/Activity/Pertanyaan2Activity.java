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
import com.example.appskimia.ui.CustomRadioGroup;
import com.example.appskimia.ui.OnCustomRadioButtonListener;

public class Pertanyaan2Activity extends AppCompatActivity {

    public static final String IT_NAMA = "nama";
    public static final String IT_PERTANYAAN1 = "pertanyaan1";
    private String mNama, mPertanyaan1, mPertanyaan2;
    private int mJawaban2;
    private Button btnPrev, btnLanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan2);
        initComponent();
        initIntent();
        initAction();
        initToolbar();
    }

    private void initIntent() {
        mNama = getIntent().getStringExtra(IT_NAMA);
        mPertanyaan1 = getIntent().getStringExtra(IT_PERTANYAAN1);
        // Toast.makeText(this, "R :"+mPertanyaan1+" : "+mNama, Toast.LENGTH_SHORT).show();

    }

    private void initComponent() {
        mNama = getIntent().getStringExtra(IT_NAMA);
        System.out.println("nama anda"+mNama);
        btnPrev =  findViewById(R.id.btn_prev);
        btnLanjut =  findViewById(R.id.btn_next);
        CustomRadioGroup.setOnClickListener((OnCustomRadioButtonListener) view -> {
            switch (view.getId()) {
                case R.id.rb_jawaban_a:
                    mJawaban2 = 0;
                    showButtonTag(mJawaban2);
                    break;
                case R.id.rb_jawaban_b:
                    mJawaban2 = 0;
                    showButtonTag(mJawaban2);
                    break;
                case R.id.rb_jawaban_c:
                    mJawaban2 = 0;
                    showButtonTag(mJawaban2);
                    break;
                case R.id.rb_jawaban_d:
                    mJawaban2 = 10;
                    showButtonTag(mJawaban2);
                    break;
                default:
                    mJawaban2 = 0;
                    showButtonTag(mJawaban2);
                    break;
            }
        });
    }

    private void showButtonTag(int viewId) {
        /*
        MainActivityRadioButtonMapper mapper = new MainActivityRadioButtonMapper(this);
        String tag = mapper.mapToStrigFrom(viewId);
        */
        mPertanyaan2= String.valueOf(viewId);
        // Toast.makeText(this, "R :"+mPertanyaan2+" : "+mNama, Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(getApplicationContext(), Pertanyaan3Activity.class);
                intent.putExtra(Pertanyaan3Activity.IT_NAMA, mNama);
                intent.putExtra(Pertanyaan3Activity.IT_PERTANYAAN1, mPertanyaan1);
                intent.putExtra(Pertanyaan3Activity.IT_PERTANYAAN2, mPertanyaan2);
                startActivity(intent);
            }
        });

    }
}
