package com.example.hajidanumroh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

public class KegiatanDetailActivity extends AppCompatActivity {
    @BindView(R.id.tv_judul_detail)
    TextView tvJudulDes;
    @BindView(R.id.tv_desc_detail)
    TextView tvIsiDesc;
    ProgressDialog loading;

    String mId;
    String mJudul;
    String mDesc;

    Context mContext;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan_detail);
        initToolbar();
        initComponent();
    }
    private void initComponent() {
        getSupportActionBar().setTitle("Kegiatan Detail");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_KEGIATAN);
        mJudul = intent.getStringExtra(Constant.KEY_JUDUL_KEGIATAN);
        mDesc = intent.getStringExtra(Constant.KEY_DES_KEGIATAN);

        tvJudulDes.setText(mJudul);
        tvIsiDesc.setText(mDesc);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_10);
        Tools.setSystemBarLight(this);
    }

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
