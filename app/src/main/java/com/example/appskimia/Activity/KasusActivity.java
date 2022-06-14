package com.example.appskimia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.opengl.ETC1;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appskimia.R;
import com.example.appskimia.Util.SharedPrefManager;
import com.example.appskimia.Util.Tools;
import com.freshchat.consumer.sdk.Freshchat;

public class KasusActivity extends AppCompatActivity {

    private Button btnSubmit, btnSolusi, btnSolusiHide, btnSubmit2, btnSolusi2, btnSolusiHide2;
    private EditText etKomentar, etKomentar2;
    private TextView tvKomentar, tvKomentar2, tvSolusi2;
    private LinearLayout llKomentar, llSolusi, llKomentar2, llSolusi2;
    private String isiKomentar, isiKomentar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasus);
        initToolbar();
        initComponent();
        initAction();
    }

    private void initAction() {
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isiKomentar = etKomentar.getText().toString();
                if (TextUtils.isEmpty(isiKomentar)){
                    Toast.makeText(getApplicationContext(), "Isi komentar...!", Toast.LENGTH_SHORT).show();
                }else{
                    llKomentar.setVisibility(View.VISIBLE);
                    tvKomentar.setText(isiKomentar);
                }

            }
        });

        btnSolusi.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                llSolusi.setVisibility(View.VISIBLE);
                btnSolusi.setVisibility(View.GONE);
                btnSolusiHide.setVisibility(View.VISIBLE);
            }
        });

        btnSolusiHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSolusi.setVisibility(View.GONE);
                btnSolusi.setVisibility(View.VISIBLE);
                btnSolusiHide.setVisibility(View.GONE);
            }
        });

        btnSubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isiKomentar2 = etKomentar2.getText().toString();
                if (TextUtils.isEmpty(isiKomentar2)){
                    Toast.makeText(getApplicationContext(), "Isi komentar...!", Toast.LENGTH_SHORT).show();
                }else{
                    llKomentar2.setVisibility(View.VISIBLE);
                    tvKomentar2.setText(isiKomentar2);
                }
            }
        });

        btnSolusi2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                llSolusi2.setVisibility(View.VISIBLE);
                btnSolusi2.setVisibility(View.GONE);
                btnSolusiHide2.setVisibility(View.VISIBLE);
            }
        });

        btnSolusiHide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                llSolusi2.setVisibility(View.GONE);
                btnSolusi2.setVisibility(View.VISIBLE);
                btnSolusiHide2.setVisibility(View.GONE);
            }
        });
    }

    private void initComponent() {
        btnSubmit = findViewById(R.id.btn_submit);
        btnSolusi = findViewById(R.id.btn_solusi);
        btnSolusiHide = findViewById(R.id.btn_solusi_hide);
        etKomentar = findViewById(R.id.et_komentar);
        tvKomentar = findViewById(R.id.tv_komentar);
        llKomentar = findViewById(R.id.ll_komentar);
        llSolusi = findViewById(R.id.ll_solusi);

        btnSubmit2 = findViewById(R.id.btn_submit2);
        btnSolusi2 = findViewById(R.id.btn_solusi2);
        btnSolusiHide2 = findViewById(R.id.btn_solusi_hide2);
        etKomentar2 = findViewById(R.id.et_komentar2);
        tvKomentar2 = findViewById(R.id.tv_komentar2);
        llKomentar2 = findViewById(R.id.ll_komentar2);
        llSolusi2 = findViewById(R.id.ll_solusi2);
        tvSolusi2 = findViewById(R.id.tv_solusi2);

        //tvSolusi2.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);

    }

    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Contoh Kasus");
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
}
