package com.example.appskimia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    static String IT_NAMA = "nama";
    private Button btnPrev;
    private String mNama;
    private int mJawaban1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan1);
        initToolbar();
        initComponent();
        initAction();
    }

    private void initAction() {

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                finish();
            }
        });

    }

    private void initComponent() {
        btnPrev =  findViewById(R.id.btn_prev);
        CustomRadioGroup.setOnClickListener((OnCustomRadioButtonListener) view -> {
            switch (view.getId()) {
                case R.id.rb_jawaban_a:
                    mJawaban1 = 10;
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
                    showButtonTag(-1);
                    break;
            }
        });
    }

    private void showButtonTag(int viewId) {
        /*
        MainActivityRadioButtonMapper mapper = new MainActivityRadioButtonMapper(this);
        String tag = mapper.mapToStrigFrom(viewId);
        */
        mNama = getIntent().getStringExtra(IT_NAMA);
        Toast.makeText(this, "R :"+String.valueOf(viewId)+" : "+mNama, Toast.LENGTH_SHORT).show();

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
