package com.example.appskimia.Activity;

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

import com.example.appskimia.Adapter.KegiatanAdapter;
import com.example.appskimia.Adapter.KegiatanPriodeAdapter;
import com.example.appskimia.Adapter.LaranganAdapter;
import com.example.appskimia.Model.Kegiatan;
import com.example.appskimia.Model.KegiatanPriode;
import com.example.appskimia.Model.Larangan;
import com.example.appskimia.R;
import com.example.appskimia.Response.ResponseKegiatan;
import com.example.appskimia.Response.ResponseKegiatanPriode;
import com.example.appskimia.Response.ResponseLarangan;
import com.example.appskimia.Util.Api.BaseApiService;
import com.example.appskimia.Util.Api.UtilsApi;
import com.example.appskimia.Util.Constant;
import com.example.appskimia.Util.RecyclerItemClickListener;
import com.example.appskimia.Util.SharedPrefManager;
import com.example.appskimia.Util.Tools;

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
    private SharedPrefManager sharedPrefManager;
    private Context mContext;
    private View parent_view;
    private RecyclerView recyclerView;
    private KegiatanPriodeAdapter kegiatanPriodeAdapter;
    private BaseApiService mApiService;
    String priode, kloter;

    List<KegiatanPriode> semuaKegiatanPriodeItemList = new ArrayList<>();
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
        sharedPrefManager = new SharedPrefManager(this);
        priode = sharedPrefManager.getSPPriode();
        kloter = sharedPrefManager.getSPKloter();

        System.out.println("ayam :"+kloter+" kucing :"+priode);

        parent_view = findViewById(android.R.id.content);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        rvKegiatan.setLayoutManager(new LinearLayoutManager(this));
        rvKegiatan.setHasFixedSize(true);

        kegiatanPriodeAdapter = new KegiatanPriodeAdapter(this, semuaKegiatanPriodeItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvKegiatan.setLayoutManager(mLayoutManager);
        rvKegiatan.setItemAnimator(new DefaultItemAnimator());

        getResultListKegiatan();

    }

    private void getResultListKegiatan(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        mApiService.getSemuaKegiatanPriode(priode,kloter).enqueue(new Callback<ResponseKegiatanPriode>() {
            @Override
            public void onResponse(Call<ResponseKegiatanPriode> call, Response<ResponseKegiatanPriode> response) {
                System.out.println("yyy: ");
                if (response.isSuccessful()){
                    loading.dismiss();
                    if (response.body().getError()){
                        tvBelumAda.setVisibility(View.VISIBLE);
                    }else{
                        final List<KegiatanPriode> semuaKegiatanPriodeItems = response.body().getKegiatan();
                        rvKegiatan.setAdapter(new KegiatanPriodeAdapter(mContext, semuaKegiatanPriodeItems));
                        kegiatanPriodeAdapter.notifyDataSetChanged();

                        initDataIntent(semuaKegiatanPriodeItems);
                    }
                }else{
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKegiatanPriode> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final List<KegiatanPriode> kegiatanList){
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
