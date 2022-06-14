package com.example.appskimia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appskimia.R;
import com.example.appskimia.Util.Tools;
import com.example.appskimia.mapper.MainActivityRadioButtonMapper;
import com.example.appskimia.ui.CustomRadioGroup;
import com.example.appskimia.ui.OnCustomRadioButtonListener;

public class EvaluasiActivity extends AppCompatActivity {
    private EditText etNama;
    private Button btnMulai;
    private String nama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluasi);
        initToolbar();
        initComponent();
        initAction();
    }

    private void initComponent() {
        etNama = findViewById(R.id.et_nama);
        btnMulai = findViewById(R.id.btn_mulai);

    }

    private void initAction() {
        btnMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(etNama.getText())){
                    etNama.setError("Harus di isi...!");
                }else{
                    nama = etNama.getText().toString();
                    Intent intent = new Intent(getApplicationContext(), Pertanyaan1Activity.class);
                    intent.putExtra(Pertanyaan1Activity.IT_NAMA, nama);
                    startActivity(intent);
                }
            }
        });
    }


    private void initToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Evaluasi");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this, R.color.green_500);
        Tools.setSystemBarLight(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
