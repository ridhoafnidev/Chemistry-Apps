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

public class KompetensiActivity extends AppCompatActivity {
    private ProgressDialog loading;
    private Context mContext;
    private View parent_view;

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
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Kompetensi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    private void initComponent() {
        getResultListKegiatan();
    }

    private void getResultListKegiatan(){
        // loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);
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
