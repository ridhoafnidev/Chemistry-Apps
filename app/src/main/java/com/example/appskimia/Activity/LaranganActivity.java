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

import com.example.appskimia.Adapter.LaranganAdapter;
import com.example.appskimia.Adapter.MateriAdapter;
import com.example.appskimia.Model.Larangan;
import com.example.appskimia.Model.Materi;
import com.example.appskimia.R;
import com.example.appskimia.Response.ResponseLarangan;
import com.example.appskimia.Response.ResponseMateri;
import com.example.appskimia.Util.Api.BaseApiService;
import com.example.appskimia.Util.Api.UtilsApi;
import com.example.appskimia.Util.Constant;
import com.example.appskimia.Util.RecyclerItemClickListener;
import com.example.appskimia.Util.Tools;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaranganActivity extends AppCompatActivity {
    @BindView(R.id.rv_larangan)
    RecyclerView rvLarangan;
    @BindView(R.id.tv_belum_larangan)
    TextView tvBelumLarangan;
    private ProgressDialog loading;

    private Context mContext;
    private View parent_view;
    private RecyclerView recyclerView;
    private LaranganAdapter laranganAdapter;
    private BaseApiService mApiService;

    List<Larangan> semualaranganItemList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_larangan);
        initToolbar();
        initComponent();
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setNavigationIcon(R.drawable.ic_menu);
        //toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.grey_80), PorterDuff.Mode.SRC_ATOP);
        //toolbar.setTitleTextColor(getResources().getColor(R.color.grey_80));
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Larangan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {

        parent_view = findViewById(android.R.id.content);
        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        rvLarangan.setLayoutManager(new LinearLayoutManager(this));
        rvLarangan.setHasFixedSize(true);

        laranganAdapter = new LaranganAdapter(this, semualaranganItemList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvLarangan.setLayoutManager(mLayoutManager);
        rvLarangan.setItemAnimator(new DefaultItemAnimator());

        getResultListLarangan();

    }

    private void getResultListLarangan(){
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        mApiService.getSemuaLarangan().enqueue(new Callback<ResponseLarangan>() {
            @Override
            public void onResponse(Call<ResponseLarangan> call, Response<ResponseLarangan> response) {
                if (response.isSuccessful()) {
                    loading.dismiss();
                    if (response.body().getError()) {
                        tvBelumLarangan.setVisibility(View.VISIBLE);
                    } else {
                        final List<Larangan> semuaLaranganItems = response.body().getLarangan();
                        rvLarangan.setAdapter(new LaranganAdapter(mContext, semuaLaranganItems));
                        laranganAdapter.notifyDataSetChanged();

                        initDataIntent(semuaLaranganItems);
                    }
                } else {
                    loading.dismiss();
                    Toast.makeText(mContext, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLarangan> call, Throwable t) {
                loading.dismiss();
                Toast.makeText(mContext, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initDataIntent(final List<Larangan> laranganList){
        rvLarangan.addOnItemTouchListener(
                new RecyclerItemClickListener(mContext, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        String id = laranganList.get(position).getIdLarangan();
                        String judul = laranganList.get(position).getJudulLarangan();
                        String desc = laranganList.get(position).getDeskripsiLarangan();

                        Intent detailLarangan = new Intent(mContext, LaranganDetailActivity.class);
                        detailLarangan.putExtra(Constant.KEY_ID_LARANGAN, id);
                        detailLarangan.putExtra(Constant.KEY_JUDUL_LARANGAN, judul);
                        detailLarangan.putExtra(Constant.KEY_DES_LARANGAN, desc);
                        startActivity(detailLarangan);
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
