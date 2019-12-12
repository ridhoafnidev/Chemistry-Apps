package com.example.hajidanumroh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hajidanumroh.R;
import com.example.hajidanumroh.Util.Api.BaseApiService;
import com.example.hajidanumroh.Util.Api.UtilsApi;
import com.example.hajidanumroh.Util.Constant;
import com.example.hajidanumroh.Util.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MateriDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_judul_detail)
    TextView tvJudulDes;
    @BindView(R.id.tv_jenis_detail)
    TextView tvJenisDes;
    @BindView(R.id.tv_desc_detail)
    TextView tvIsiDesc;
    ProgressDialog loading;

    String mId;
    String mJudulMateri;
    String mJenisMateri;
    String mDescMateri;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi_detail);
        initToolbar();
        initComponent();
    }

    private void initComponent() {
        getSupportActionBar().setTitle("Materi Detail");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_MATERI);
        mJudulMateri = intent.getStringExtra(Constant.KEY_JUDUL_MATERI);
        mJenisMateri = intent.getStringExtra(Constant.KEY_JENIS_MATERI);
        mDescMateri = intent.getStringExtra(Constant.KEY_DES_MATERI);

        tvJudulDes.setText(mJudulMateri);
        tvJenisDes.setText(mJenisMateri);
        tvIsiDesc.setText(mDescMateri);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_10);
        Tools.setSystemBarLight(this);
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_share_save, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
        return true;
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle() + " clicked", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
