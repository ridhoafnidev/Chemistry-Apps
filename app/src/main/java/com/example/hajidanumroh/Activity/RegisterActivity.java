package com.example.hajidanumroh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hajidanumroh.R;
import com.example.hajidanumroh.Util.Api.BaseApiService;
import com.example.hajidanumroh.Util.Api.UtilsApi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.et_username) EditText etUsername;
    @BindView(R.id.et_nama_awal) EditText etNamaAwal;
    @BindView(R.id.et_nama_akhir) EditText etNamaAkhir;
    @BindView(R.id.et_email) EditText etEmail;
    @BindView(R.id.et_no_hp) EditText etNoHp;
    @BindView(R.id.et_alamat) EditText etAlamat;
    @BindView(R.id.et_password) EditText etPassword;
    @BindView(R.id.btnRegister) Button btnRegister;
    ProgressDialog loading;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //getSupportActionBar().hide();

        ButterKnife.bind(this);
        mContext = this;
        mApiService = UtilsApi.getAPIService();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                String username = etUsername.getText().toString();
                String nama_awal = etNamaAwal.getText().toString();
                String nama_akhir = etNamaAkhir.getText().toString();
                String email = etEmail.getText().toString();
                String no_hp = etNoHp.getText().toString();
                String alamat = etAlamat.getText().toString();
                String password = etPassword.getText().toString();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(nama_awal) || TextUtils.isEmpty(nama_akhir) ||
                    TextUtils.isEmpty(email) || TextUtils.isEmpty(no_hp) || TextUtils.isEmpty(alamat) || TextUtils.isEmpty(alamat) || TextUtils.isEmpty(password))
                {
                    Toast.makeText(mContext, "Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else{
                    requestRegister();
                }
            }
        });
    }

    private void requestRegister(){
        mApiService.registerRequest(
                etUsername.getText().toString(),
                etNamaAwal.getText().toString(),
                etNamaAkhir.getText().toString(),
                etEmail.getText().toString(),
                etNoHp.getText().toString(),
                etAlamat.getText().toString(),
                etPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                } else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Log.i("debug", "onResponse: GA BERHASIL");
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
