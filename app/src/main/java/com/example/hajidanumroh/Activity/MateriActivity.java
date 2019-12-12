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

import com.example.hajidanumroh.Adapter.MateriAdapter;
import com.example.hajidanumroh.Model.Materi;
import com.example.hajidanumroh.R;
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

public class MateriActivity extends AppCompatActivity {
    @BindView(R.id.rv_materi)
    RecyclerView rvMateri;
    @BindView(R.id.tv_belum_materi)
    TextView tvBelumMateri;
    private ProgressDialog loading;

    private Context mContext;
    private View parent_view;
    private RecyclerView recyclerView;
    private MateriAdapter materiAdapter;
    private BaseApiService mApiService;

    List<Materi> semuamateriItemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materi);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_menu);
        //toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_ATOP);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.grey_80));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Materi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {

        parent_view = findViewById(android.R.id.content);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        rvMateri.setLayoutManager(new LinearLayoutManager(this));
        rvMateri.setHasFixedSize(true);

        materiAdapter = new MateriAdapter(this, semuamateriItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvMateri.setLayoutManager(mLayoutManager);
        rvMateri.setItemAnimator(new DefaultItemAnimator());

        getResultListDosen();

    }

    private void getResultListDosen(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        mApiService.getSemuaMateri().enqueue(new Callback<ResponseMateri>() {
            @Override
            public void onResponse(Call<ResponseMateri> call, Response<ResponseMateri> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    if (response.body().getError()) {
                        tvBelumMateri.setVisibility(View.VISIBLE);
                    } else {
                        final List<Materi> semuamatkulItems = response.body().getMateri();
                        rvMateri.setAdapter(new MateriAdapter(mContext, semuamatkulItems));
                        materiAdapter.notifyDataSetChanged();

                        initDataIntent(semuamatkulItems);
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
        rvMateri.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        String id = materiList.get(position).getIdMateri();
                        String judul = materiList.get(position).getJudulMateri();
                        String jenis = materiList.get(position).getJenisMateri();
                        String desc = materiList.get(position).getDeskripsiMateri();

                        Intent detailMatkul = new Intent(mContext, MateriDetailActivity.class);
                        detailMatkul.putExtra(Constant.KEY_ID_MATERI, id);
                        detailMatkul.putExtra(Constant.KEY_JUDUL_MATERI, judul);
                        detailMatkul.putExtra(Constant.KEY_JENIS_MATERI, jenis);
                        detailMatkul.putExtra(Constant.KEY_DES_MATERI, desc);
                        startActivity(detailMatkul);
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
