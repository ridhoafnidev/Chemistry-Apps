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

import com.example.hajidanumroh.Adapter.KegiatanAdapter;
import com.example.hajidanumroh.Adapter.LaranganAdapter;
import com.example.hajidanumroh.Model.Kegiatan;
import com.example.hajidanumroh.Model.Larangan;
import com.example.hajidanumroh.R;
import com.example.hajidanumroh.Response.ResponseKegiatan;
import com.example.hajidanumroh.Response.ResponseLarangan;
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

public class KegiatanActivity extends AppCompatActivity {
    @BindView(R.id.rv_kegiatan)
    RecyclerView rvKegiatan;
    @BindView(R.id.tv_belum)
    TextView tvBelumAda;
    private ProgressDialog loading;

    private Context mContext;
    private View parent_view;
    private RecyclerView recyclerView;
    private KegiatanAdapter kegiatanAdapter;
    private BaseApiService mApiService;

    List<Kegiatan> semuaKegiatanItemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_menu);
        //toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_ATOP);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.grey_80));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kegiatan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {

        parent_view = findViewById(android.R.id.content);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        rvKegiatan.setLayoutManager(new LinearLayoutManager(this));
        rvKegiatan.setHasFixedSize(true);

        kegiatanAdapter = new KegiatanAdapter(this, semuaKegiatanItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvKegiatan.setLayoutManager(mLayoutManager);
        rvKegiatan.setItemAnimator(new DefaultItemAnimator());

        getResultListKegiatan();

    }

    private void getResultListKegiatan(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        mApiService.getSemuaKegiatan().enqueue(new Callback<ResponseKegiatan>() {
            @Override
            public void onResponse(Call<ResponseKegiatan> call, Response<ResponseKegiatan> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    if (response.body().getError()) {
                        tvBelumAda.setVisibility(View.VISIBLE);
                    } else {
                        final List<Kegiatan> semuaKegiatanItems = response.body().getKegiatan();
                        rvKegiatan.setAdapter(new KegiatanAdapter(mContext, semuaKegiatanItems));
                        kegiatanAdapter.notifyDataSetChanged();

                        initDataIntent(semuaKegiatanItems);
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKegiatan> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final List<Kegiatan> kegiatanList){
        rvKegiatan.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        String id = kegiatanList.get(position).getIdKegiatan();
                        String judul = kegiatanList.get(position).getJudulKegiatan();
                        String desc = kegiatanList.get(position).getDeskripsiKegiatan();

                        Intent detailKegiatan = new Intent(mContext, KegiatanDetailActivity.class);
                        detailKegiatan.putExtra(Constant.KEY_ID_KEGIATAN, id);
                        detailKegiatan.putExtra(Constant.KEY_JUDUL_KEGIATAN, judul);
                        detailKegiatan.putExtra(Constant.KEY_DES_KEGIATAN, desc);
                        startActivity(detailKegiatan);
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
