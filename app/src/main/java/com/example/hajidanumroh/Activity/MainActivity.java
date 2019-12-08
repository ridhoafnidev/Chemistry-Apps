package com.example.hajidanumroh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hajidanumroh.R;
import com.example.hajidanumroh.Util.Api.BaseApiService;
import com.example.hajidanumroh.Util.Api.UtilsApi;
import com.example.hajidanumroh.Util.SharedPrefManager;
import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatConfig;
import com.freshchat.consumer.sdk.FreshchatUser;
import com.freshchat.consumer.sdk.exception.MethodNotAllowedException;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button btnShowConversations, btnShowFAQs, btnLogout;
    private TextView textView, tvNama, tvEmail, tvHp;
    String nama_lengkap, email, nomor_hp, id, restoreId, externalId, SessionRestoreId;

    SharedPrefManager sharedPrefManager;
    Context mContext;
    BaseApiService mApiService;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        textView = findViewById(R.id.tv_);
        tvNama = findViewById(R.id.tv_nama);
        tvEmail = findViewById(R.id.tv_email);
        tvHp = findViewById(R.id.tv_hp);
        sharedPrefManager = new SharedPrefManager(this);

        nama_lengkap = sharedPrefManager.getSPNama();
        id = sharedPrefManager.getSPId();
        email = sharedPrefManager.getSPEmail();
        nomor_hp = sharedPrefManager.getSPNomorHp();

        externalId = id;
        SessionRestoreId = sharedPrefManager.getSPRestoreId();

        try {
            Freshchat.getInstance(getApplicationContext()).identifyUser(externalId, null);
        } catch (MethodNotAllowedException e) {
            e.printStackTrace();
        }

        restoreId = receiveFreschatRestoreId();
        Freshchat freshchat = Freshchat.getInstance(getApplicationContext());
        if (TextUtils.isEmpty(restoreId)){
            try {
                freshchat.identifyUser(externalId, null);
            } catch (MethodNotAllowedException e) {
                e.printStackTrace();
            }

            String newRestoreId = freshchat.getUser().getRestoreId();
            if (TextUtils.isEmpty(newRestoreId)){
                Toast.makeText(this, "Ini...", Toast.LENGTH_SHORT).show();
                receiveFreschatRestoreId();
            }else{
                saveRestoreIdForUser(newRestoreId);
            }
        }else{
            try {
                freshchat.identifyUser(id, SessionRestoreId);
            } catch (MethodNotAllowedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Restore ID :"+restoreId);
        System.out.println("Restore ID Session :"+SessionRestoreId);

        /*
        if (SessionRestoreId != null){
            saveRestoreIdForUser(restoreId);
        }else{
            Log.i("debug", "onResponse: RestoreId sudah ada");
        }
        */

        textView.setText(sharedPrefManager.getSPRestoreId());
        tvNama.setText(sharedPrefManager.getSPNama());
        tvEmail.setText(sharedPrefManager.getSPEmail());
        tvHp.setText(sharedPrefManager.getSPNomorHp());

        //init
        FreshchatConfig freshchatConfig=new FreshchatConfig("aa4c2c01-93e1-42dd-845d-723ecba685b4","67a3eac3-43ab-468e-984d-d95a6aa9c014");
        freshchatConfig.setCameraCaptureEnabled(true);
        freshchatConfig.setGallerySelectionEnabled(true);
        Freshchat.getInstance(getApplicationContext()).init(freshchatConfig);
        //Update user information
        FreshchatUser user = Freshchat.getInstance(getApplicationContext()).getUser();
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(nomor_hp)){
            Toast.makeText(mContext, "Kosong", Toast.LENGTH_SHORT).show();
        }else{
            user.setFirstName(nama_lengkap).setEmail(email).setPhone("+628", nomor_hp);
            //System.out.println("Restore nama:"+nama_lengkap+" email:"+email+" nomor hp:"+nomor_hp);
            try {
                Freshchat.getInstance(getApplicationContext()).setUser(user);
                Freshchat.getInstance(getApplicationContext()).identifyUser(id, SessionRestoreId);
            } catch (MethodNotAllowedException e) {
                Log.e("FreshchatError", e.toString());
            }
        }

        // identify user
        /*
        try {
            Freshchat.getInstance(getApplicationContext()).identifyUser(externalId, restoreId);
        } catch (MethodNotAllowedException e) {
            e.printStackTrace();
        }
        */

        btnShowFAQs = (Button) findViewById(R.id.btnShowFAQs);
        btnShowConversations = (Button) findViewById(R.id.btnShowConversations);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnShowConversations.setOnClickListener(viewClickListener);
        btnShowFAQs.setOnClickListener(viewClickListener);
        btnLogout.setOnClickListener(viewClickListener);

    }

    private String receiveFreschatRestoreId() {
        IntentFilter intentFilter = new IntentFilter(Freshchat.FRESHCHAT_USER_RESTORE_ID_GENERATED);
        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                restoreId = Freshchat.getInstance(context).getUser().getRestoreId();
                if (TextUtils.isEmpty(restoreId)){
                     receiveFreschatRestoreId2();
                }else{
                    saveRestoreIdForUser(restoreId);
                }
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
        return restoreId;
    }

    private String receiveFreschatRestoreId2() {
        IntentFilter intentFilter = new IntentFilter(Freshchat.FRESHCHAT_USER_RESTORE_ID_GENERATED);
        final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                restoreId = Freshchat.getInstance(context).getUser().getRestoreId();
            }
        };
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
        return restoreId;
    }

    private void saveRestoreIdForUser(String restoreId) {
        mApiService.updateRequest(id, restoreId)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse: BERHASIL");
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    //Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
                                    //startActivity(new Intent(mContext, LoginActivity.class));
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
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick (View v) {
            if(v.getId() == R.id.btnShowFAQs) {

                Freshchat.showFAQs(MainActivity.this);

            } else if(v.getId() == R.id.btnShowConversations) {

                Freshchat.showConversations(MainActivity.this);

            }else if(v.getId() == R.id.btnLogout) {
                Freshchat.resetUser(getApplicationContext());
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(MainActivity.this, LoginActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                finish();

            }
        }
    };

}
