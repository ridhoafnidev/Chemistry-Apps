package com.example.hajidanumroh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

public class VideoDetailActivity extends AppCompatActivity {
    @BindView(R.id.tv_judul_detail)
    TextView tvJudulDes;
    @BindView(R.id.tv_desc_detail)
    TextView tvIsiDesc;
    @BindView(R.id.wv_yt)
    WebView wvYt;
    ProgressDialog loading;

    String mId;
    String mJudul;
    String mJenis;
    String mDesc;
    String mVideo;

    Context mContext;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        initToolbar();
        initComponent();
    }
    private void initComponent() {
        getSupportActionBar().setTitle("Do'a Detail");

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        Intent intent = getIntent();
        mId = intent.getStringExtra(Constant.KEY_ID_MATERI);
        mJudul = intent.getStringExtra(Constant.KEY_JUDUL_MATERI);
        mDesc = intent.getStringExtra(Constant.KEY_DES_MATERI);
        mVideo = intent.getStringExtra(Constant.KEY_VIDEO_MATERI);

        tvJudulDes.setText(mJudul);
        tvIsiDesc.setText(mDesc);

        // setting
        wvYt.setWebViewClient(new WebViewClient());
        wvYt.setWebChromeClient(new WebChromeClient());
        wvYt.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wvYt.getSettings().setJavaScriptEnabled(true);
        wvYt.getSettings().setPluginState(WebSettings.PluginState.ON);
        wvYt.getSettings().setDefaultFontSize(18);

        String xml =  mVideo;
        System.out.println("cacing yt: "+xml);
        String[] explode = xml.split("=");
        System.out.println("cacing: "+explode[1]);

        String kodeHTML = "<head></head><body>"+
                "<iframe width=\"348\" height=\"160\" src=\"https://www.youtube.com/embed/"+explode[1]+
                "\" frameborder=\"0\" allow=\"autoplay; encrypted-media\" allowfullscreen></iframe>"+
                "</body>";
        wvYt.loadData(kodeHTML,"text/html; charset=utf-8",null);

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