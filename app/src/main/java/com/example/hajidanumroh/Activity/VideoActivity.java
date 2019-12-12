package com.example.hajidanumroh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hajidanumroh.Adapter.MateriVideoAdapter;
import com.example.hajidanumroh.Model.Doa;
import com.example.hajidanumroh.Model.Materi;
import com.example.hajidanumroh.R;
import com.example.hajidanumroh.Response.ResponseDoa;
import com.example.hajidanumroh.Response.ResponseMateri;
import com.example.hajidanumroh.Util.Api.BaseApiService;
import com.example.hajidanumroh.Util.Api.UtilsApi;
import com.example.hajidanumroh.Util.Constant;
import com.example.hajidanumroh.Util.RecyclerItemClickListener;
import com.example.hajidanumroh.Util.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoActivity extends AppCompatActivity {
    @BindView(R.id.rv_video)
    RecyclerView rvDoaVideo;
    @BindView(R.id.tv_belum)
    TextView tvBelumAda;
    private ProgressDialog loading;

    private Context mContext;
    private View parent_view;
    private MateriVideoAdapter materiVideoAdapter;
    private BaseApiService mApiService;

    List<Materi> semuaDoaItemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_menu);
        //toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_ATOP);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.grey_80));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Materi Video");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {

        parent_view = findViewById(android.R.id.content);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        rvDoaVideo.setLayoutManager(new LinearLayoutManager(this));
        rvDoaVideo.setHasFixedSize(true);

        materiVideoAdapter = new MateriVideoAdapter(this, semuaDoaItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvDoaVideo.setLayoutManager(mLayoutManager);
        rvDoaVideo.setItemAnimator(new DefaultItemAnimator());

        getResultListDoaVideo();

    }

    private void getResultListDoaVideo(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        mApiService.getSemuaMateri().enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    if (response.body().getError()) {
                        tvBelumAda.setVisibility(View.VISIBLE);
                    } else {
                        final List<Materi> semuaMateriItems = response.body().getMateri();
                        rvDoaVideo.setAdapter(new MateriVideoAdapter(mContext, semuaMateriItems));
                        materiVideoAdapter.notifyDataSetChanged();

                        initDataIntent(semuaMateriItems);
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseMateri> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final List<Materi> materiList){
        rvDoaVideo.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        String id = materiList.get(position).getIdMateri();
                        String judul = materiList.get(position).getJudulMateri();
                        String desc = materiList.get(position).getDeskripsiMateri();
                        String yt = materiList.get(position).getLinkYoutube();

                        Intent detailVideo = new Intent(mContext, VideoDetailActivity.class);
                        detailVideo.putExtra(Constant.KEY_ID_MATERI, id);
                        detailVideo.putExtra(Constant.KEY_JUDUL_MATERI, judul);
                        detailVideo.putExtra(Constant.KEY_DES_MATERI, desc);
                        detailVideo.putExtra(Constant.KEY_VIDEO_MATERI, yt);
                        startActivity(detailVideo);
                    }
                }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_80));
        return true;
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
