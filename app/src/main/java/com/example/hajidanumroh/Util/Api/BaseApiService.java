package com.example.hajidanumroh.Util.Api;

import com.example.hajidanumroh.Response.ResponseDoa;
import com.example.hajidanumroh.Response.ResponseKegiatan;
import com.example.hajidanumroh.Response.ResponseLarangan;
import com.example.hajidanumroh.Response.ResponseMateri;
import com.example.hajidanumroh.Response.ResponseUserDetail;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface BaseApiService {

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/login.php
    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> loginRequest(@Field("username") String username,
                                    @Field("password") String password);

    // Fungsi ini untuk memanggil API http://10.0.2.2/mahasiswa/register.php
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> registerRequest(
                                       @Field("username") String username,
                                       @Field("nama_awal") String nama_awal,
                                       @Field("nama_akhir") String nama_akhir,
                                       @Field("email") String email,
                                       @Field("nomor_hp") String nomor_hp,
                                       @Field("alamat") String alamat,
                                       @Field("password") String password);

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseBody> updateRequest(@Field("id_user") String id_user,
                                     @Field("restore_id") String restore_id);

    @GET("materi")
    Call<ResponseMateri> getSemuaMateri();

    @GET("larangan")
    Call<ResponseLarangan> getSemuaLarangan();

    @GET("doa")
    Call<ResponseDoa> getSemuaDoa();

    @GET("kegiatan")
    Call<ResponseKegiatan> getSemuaKegiatan();


    @GET("user/{id}")
    Call<ResponseUserDetail> getDetailUser(@Path("id") String id);

    /*
    @FormUrlEncoded
    @POST("matkul")
    Call<ResponseBody> simpanMatkulRequest(@Field("nama_dosen") String namadosen,
                                           @Field("matkul") String namamatkul);

    @DELETE("matkul/{idmatkul}")
    Call<ResponseBody> deteleMatkul(@Path("idmatkul") String idmatkul);
    */
}
