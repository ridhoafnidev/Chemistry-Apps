package com.example.appskimia.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    public static final String SP_MAHASISWA_APP = "spHajiUmrahApp";

    public static final String SP_ID = "spId";
    public static final String SP_NAMA_AWAL = "spNamaAwal";
    public static final String SP_PRIODE = "spPriode";
    public static final String SP_KLOTER = "spKloter";
    public static final String SP_NAMA_AKHIR = "spNamaAkhir";
    public static final String SP_EMAIL = "spEmail";
    public static final String SP_NOMOR_HP = "spNomorHp";
    public static final String SP_RESTORE_ID = "spRestoreId";
    public static final String SP_ALAMAT = "spAlamat";

    public static final String SP_SUDAH_LOGIN = "spSudahLogin";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SharedPrefManager(Context context){
        sp = context.getSharedPreferences(SP_MAHASISWA_APP, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP, value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP, value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP, value);
        spEditor.commit();
    }

    public String getSPNamaAwal(){
        return sp.getString(SP_NAMA_AWAL, "");
    }

    public String getSPNamaAkhir(){
        return sp.getString(SP_NAMA_AKHIR, "");
    }

    public String getSPId(){
        return sp.getString(SP_ID, "");
    }

    public String getSPEmail(){
        return sp.getString(SP_EMAIL, "");
    }

    public String getSPNomorHp(){
        return sp.getString(SP_NOMOR_HP, "");
    }

    public String getSPRestoreId(){
        return sp.getString(SP_RESTORE_ID, "");
    }

    public String getSPKloter(){
        return sp.getString(SP_KLOTER, "");
    }

    public String getSPPriode(){
        return sp.getString(SP_PRIODE, "");
    }

    public Boolean getSPSudahLogin(){
        return sp.getBoolean(SP_SUDAH_LOGIN, false);
    }
}
