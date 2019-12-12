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

import com.example.hajidanumroh.Adapter.DoaAdapter;
import com.example.hajidanumroh.Adapter.LaranganAdapter;
import com.example.hajidanumroh.Model.Doa;
import com.example.hajidanumroh.Model.Larangan;
import com.example.hajidanumroh.R;
import com.example.hajidanumroh.Response.ResponseDoa;
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

public class DoaActivity extends AppCompatActivity {
    @BindView(R.id.rv_doa)
    RecyclerView rvDoa;
    @BindView(R.id.tv_belum)
    TextView tvBelumAda;
    private ProgressDialog loading;

    private Context mContext;
    private View parent_view;
    private DoaAdapter doaAdapter;
    private BaseApiService mApiService;

    List<Doa> semuaDoaItemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doa);

        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_menu);
        //toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_ATOP);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.grey_80));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Do'a");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {

        parent_view = findViewById(android.R.id.content);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        rvDoa.setLayoutManager(new LinearLayoutManager(this));
        rvDoa.setHasFixedSize(true);

        doaAdapter = new DoaAdapter(this, semuaDoaItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvDoa.setLayoutManager(mLayoutManager);
        rvDoa.setItemAnimator(new DefaultItemAnimator());

        getResultListDoa();

    }

    private void getResultListDoa(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        mApiService.getSemuaDoa().enqueue(new Callback<ResponseDoa>() {
            @Override
            public void onResponse(Call<ResponseDoa> call, Response<ResponseDoa> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    if (response.body().getError()) {
                        tvBelumAda.setVisibility(View.VISIBLE);
                    } else {
                        final List<Doa> semuaDoaItems = response.body().getDoa();
                        rvDoa.setAdapter(new DoaAdapter(mContext, semuaDoaItems));
                        doaAdapter.notifyDataSetChanged();

                        initDataIntent(semuaDoaItems);
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDoa> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final List<Doa> doaList){
        rvDoa.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        String id = doaList.get(position).getIdDoa();
                        String judul = doaList.get(position).getJudulDoa();
                        String desc = doaList.get(position).getDeskripsiDoa();
                        String image = doaList.get(position).getGambarDoa();

                        Intent detailDoa = new Intent(mContext, DoaDetailActivity.class);
                        detailDoa.putExtra(Constant.KEY_ID_DOA, id);
                        detailDoa.putExtra(Constant.KEY_JUDUL_DOA, judul);
                        detailDoa.putExtra(Constant.KEY_DES_DOA, desc);
                        detailDoa.putExtra(Constant.KEY_IMAGE_DOA, image);
                        startActivity(detailDoa);
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
