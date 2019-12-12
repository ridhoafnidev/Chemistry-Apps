package com.example.hajidanumroh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hajidanumroh.R;
import com.example.hajidanumroh.Util.Api.BaseApiService;
import com.example.hajidanumroh.Util.Api.ServerConfig;
import com.example.hajidanumroh.Util.Api.UtilsApi;
import com.example.hajidanumroh.Util.Constant;
import com.example.hajidanumroh.Util.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoaDetailActivity extends AppCompatActivity {
    @BindView(R.id.tv_judul_detail)
    TextView tvJudulDes;
    @BindView(R.id.tv_desc_detail)
    TextView tvIsiDesc;
    @BindView(R.id.iv_image)
    ImageView tvImage;
    ProgressDialog loading;

    String mId;
    String mJudul;
    String mJenis;
    String mDesc;
    String mImage;

    Context mContext;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa_detail);
        initToolbar();
        initComponent();
    }
    private void initComponent() {
        getSupportActionBar().setTitle("Do'a Detail");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_DOA);
        mJudul = intent.getStringExtra(Constant.KEY_JUDUL_DOA);
        mDesc = intent.getStringExtra(Constant.KEY_DES_DOA);
        mImage = intent.getStringExtra(Constant.KEY_IMAGE_DOA);

        tvJudulDes.setText(mJudul);
        tvIsiDesc.setText(mDesc);
        Glide.with(mContext)
                .load(ServerConfig.DOA_PATH+mImage)
                .into(tvImage);
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