package com.example.appskimia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.appskimia.Adapter.AdapterSnapGeneric;
import com.example.appskimia.Data.DataGenerator;
import com.example.appskimia.Helper.StartSnapHelper;
import com.example.appskimia.Model.Image;
import com.example.appskimia.R;
import com.example.appskimia.Util.Tools;

import java.util.List;

public class HajiUmrahActivity extends AppCompatActivity {
    private CardView cvHajiDanUmrah, cvVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_haji_umrah);
        initToolbar();
        initComponent();
    }

    private void initComponent() {

        cvHajiDanUmrah = findViewById(R.id.cv_haji_dan_umrah);
        cvVideo = findViewById(R.id.cv_video);

        cvHajiDanUmrah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(HajiUmrahActivity.this, MateriActivity.class);
                HajiUmrahActivity.this.startActivity(myIntent);
            }
        });

        cvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), VideoActivity.class));
            }
        });

        RecyclerView recyclerStart = findViewById(R.id.recyclerStart);

        recyclerStart.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        layoutManager.setReverseLayout(true);
        recyclerStart.setLayoutManager(layoutManager);

        // generate data
        List<Image> items = DataGenerator.getImageDate(this);

        //set data and list adapter
        recyclerStart.setAdapter(new AdapterSnapGeneric(this, items, R.layout.item_snap_basic));
        recyclerStart.setOnFlingListener(null);
        new StartSnapHelper().attachToRecyclerView(recyclerStart);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Haji dan Umrah");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.grey_5);
        Tools.setSystemBarLight(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_setting, menu);
        Tools.changeMenuIconColor(menu, getResources().getColor(R.color.grey_60));
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