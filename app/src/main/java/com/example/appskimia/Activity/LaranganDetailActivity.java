package com.example.appskimia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appskimia.R;
import com.example.appskimia.Util.Api.BaseApiService;
import com.example.appskimia.Util.Api.UtilsApi;
import com.example.appskimia.Util.Constant;
import com.example.appskimia.Util.Tools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaranganDetailActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_larangan_detail);
        initToolbar();
        initComponent();
    }
    private void initComponent() {
        getSupportActionBar().setTitle("Larangan Detail");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_LARANGAN);
        mJudul = intent.getStringExtra(Constant.KEY_JUDUL_LARANGAN);
        mDesc = intent.getStringExtra(Constant.KEY_DES_LARANGAN);

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
