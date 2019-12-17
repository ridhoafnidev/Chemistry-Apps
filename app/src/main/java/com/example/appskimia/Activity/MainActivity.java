package com.example.appskimia.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appskimia.Adapter.AdapterSnapGeneric;
import com.example.appskimia.BuildConfig;
import com.example.appskimia.Data.DataGenerator;
import com.example.appskimia.Helper.StartSnapHelper;
import com.example.appskimia.Model.Image;
import com.example.appskimia.R;
import com.example.appskimia.Response.ResponseUserDetail;
import com.example.appskimia.Util.Api.BaseApiService;
import com.example.appskimia.Util.Api.UtilsApi;
import com.example.appskimia.Util.SharedPrefManager;
import com.example.appskimia.Util.Tools;
import com.freshchat.consumer.sdk.Freshchat;
import com.freshchat.consumer.sdk.FreshchatConfig;
import com.freshchat.consumer.sdk.FreshchatUser;
import com.freshchat.consumer.sdk.exception.MethodNotAllowedException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private CardView cvKegiatan, cvLarangan, cvDoa, cvAsq, cvTentang;
    private LinearLayout cvHajiUmrah;
    SharedPrefManager sharedPrefManager;
    Context mContext;
    BaseApiService mApiService;
    BroadcastReceiver broadcastReceiver;
    String nama_awal, nama_akhir, email, nomor_hp, id, restoreId, externalId, SessionRestoreId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initComponent();
        initFreshChat();
        initAction();
    }

    private void initFreshChat() {

        sharedPrefManager = new SharedPrefManager(this);

        nama_awal = sharedPrefManager.getSPNamaAwal();
        nama_akhir = sharedPrefManager.getSPNamaAkhir();
        id = sharedPrefManager.getSPId();
        email = sharedPrefManager.getSPEmail();
        nomor_hp = sharedPrefManager.getSPNomorHp();

        externalId = id;
        SessionRestoreId = sharedPrefManager.getSPRestoreId();

        /*
        try {
            Freshchat.getInstance(getApplicationContext()).identifyUser(externalId, null);
        } catch (MethodNotAllowedException e) {
            e.printStackTrace();
        }
        */

        restoreId = receiveFreschatRestoreId();

        if (TextUtils.isEmpty(SessionRestoreId)){
            saveRestoreIdForUser(restoreId);
        }else{
            System.out.println("Data kosong");
        }

        Freshchat freshchat = Freshchat.getInstance(getApplicationContext());
        if (TextUtils.isEmpty(restoreId)){
            try {
                freshchat.identifyUser(externalId, null);
            } catch (MethodNotAllowedException e) {
                e.printStackTrace();
            }

            String newRestoreId = freshchat.getUser().getRestoreId();
            if (TextUtils.isEmpty(newRestoreId)){
                // Toast.makeText(this, "Ini...", Toast.LENGTH_SHORT).show();
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

        //textView.setText(sharedPrefManager.getSPRestoreId());
        //tvNama.setText(sharedPrefManager.getSPNama());
        //tvEmail.setText(sharedPrefManager.getSPEmail());
        //tvHp.setText(sharedPrefManager.getSPNomorHp());

        //init
        FreshchatConfig freshchatConfig=new FreshchatConfig("aa4c2c01-93e1-42dd-845d-723ecba685b4","67a3eac3-43ab-468e-984d-d95a6aa9c014");
        freshchatConfig.setCameraCaptureEnabled(true);
        freshchatConfig.setGallerySelectionEnabled(true);
        Freshchat.getInstance(getApplicationContext()).init(freshchatConfig);
        //Update user information
        FreshchatUser user = Freshchat.getInstance(getApplicationContext()).getUser();
        if (TextUtils.isEmpty(email) && TextUtils.isEmpty(nomor_hp)){
            // Toast.makeText(mContext, "Kosong", Toast.LENGTH_SHORT).show();
        }else{
            mApiService.getDetailUser(id).enqueue(new Callback<ResponseUserDetail>() {
                @Override
                public void onResponse(Call<ResponseUserDetail> call, Response<ResponseUserDetail> response) {
                    System.out.println(response.toString());

                    if (response.isSuccessful()){
                        String mNamaAwal = response.body().getNamaAwal();
                        String mNamaAkhir = response.body().getNamaAkhir();
                        String mNoHp = response.body().getNomorHp();
                        String mEmail = response.body().getEmail();
                        user.setFirstName(mNamaAwal).setLastName(mNamaAkhir).setEmail(mEmail).setPhone("+628", mNoHp);
                        //System.out.println("Restore nama:"+nama_lengkap+" email:"+email+" nomor hp:"+nomor_hp);
                        /* Set any custom metadata to give agents more context, and for segmentation for marketing or pro-active messaging */
//                        Map<String, String> userMeta = new HashMap<String, String>();
//                        userMeta.put("alamat", response.body().getAlamat());
//                        userMeta.put("nama_awal", response.body().getNamaAwal());
//                        userMeta.put("nama_akhir", response.body().getNamaAkhir());
//                        userMeta.put("email", response.body().getEmail());
//                        userMeta.put("nomor_hp", response.body().getNomorHp());
//                        userMeta.put("username", response.body().getUsername());
//                        userMeta.put("restore_id", response.body().getRestoreId());

                        try {
                            Freshchat.getInstance(getApplicationContext()).setUser(user);
                            Freshchat.getInstance(getApplicationContext()).identifyUser(sharedPrefManager.getSPId(), sharedPrefManager.getSPRestoreId());
                        } catch (MethodNotAllowedException e) {
                            Log.e("FreshchatError", e.toString());
                        }

                        //Call setUserProperties to sync the user properties with Freshchat's servers
//                        try {
//                            Freshchat.getInstance(getApplicationContext()).setUserProperties(userMeta);
//                        } catch (MethodNotAllowedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseUserDetail> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }

    private void getUserDetail() {

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
                                    // Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
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

    private void initAction() {
        cvHajiUmrah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, HajiUmrahActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        cvKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KegiatanActivity.class));
            }
        });

        cvLarangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LaranganActivity.class));
            }
        });

        cvDoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DoaActivity.class));
            }
        });

        cvKegiatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), KegiatanActivity.class));
            }
        });

        cvAsq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchFreshchat();
            }
        });

        ((CardView) findViewById(R.id.cv_tentang)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAbout();
            }
        });
    }

    private void launchFreshchat() {
        Freshchat.showConversations(MainActivity.this);
    }

    private void initComponent() {

        mContext = this;
        mApiService = UtilsApi.getAPIService();

        sharedPrefManager = new SharedPrefManager(this);

        String sEmail = sharedPrefManager.getSPEmail();

        System.out.println("kambing: "+sEmail);

        cvHajiUmrah = findViewById(R.id.cv_haji_umrah);
        cvKegiatan = findViewById(R.id.cv_kegiatan);
        cvLarangan = findViewById(R.id.cv_larangan);
        cvDoa = findViewById(R.id.cv_doa);
        cvAsq = findViewById(R.id.cv_asq_que);
        cvTentang = findViewById(R.id.cv_tentang);

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

    private void showDialogAbout() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_about);
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        ((TextView) dialog.findViewById(R.id.tv_version)).setText("Version " + BuildConfig.VERSION_NAME);

        ((View) dialog.findViewById(R.id.bt_getcode)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://codecanyon.net/user/dream_space/portfolio"));
                startActivity(i);
                */
            }
        });

        ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        /*
        ((Button) dialog.findViewById(R.id.bt_portfolio)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Tools.openInAppBrowser(AboutDialogMainAction.this, "http://portfolio.dream-space.web.id/", false);
            }
        });
        */

        dialog.show();
        dialog.getWindow().setAttributes(lp);
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
            Freshchat.resetUser(getApplicationContext());
            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
            //startActivity(new Intent(MainActivity.this, LoginActivity.class)
            //        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
            //Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

}
