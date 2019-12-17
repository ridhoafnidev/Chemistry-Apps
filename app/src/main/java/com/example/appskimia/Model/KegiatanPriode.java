package com.example.appskimia.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KegiatanPriode {
    @SerializedName("id_kegiatan")
    @Expose
    private String idKegiatan;
    @SerializedName("kloter")
    @Expose
    private String kloter;
    @SerializedName("priode")
    @Expose
    private String priode;
    @SerializedName("tanggal_keberangkatan")
    @Expose
    private String tanggalKeberangkatan;
    @SerializedName("tanggal_kepulangan")
    @Expose
    private String tanggalKepulangan;
    @SerializedName("judul_kegiatan")
    @Expose
    private String judulKegiatan;
    @SerializedName("deskripsi_kegiatan")
    @Expose
    private String deskripsiKegiatan;

    public String getIdKegiatan() {
        return idKegiatan;
    }

    public void setIdKegiatan(String idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    public String getKloter() {
        return kloter;
    }

    public void setKloter(String kloter) {
        this.kloter = kloter;
    }

    public String getPriode() {
        return priode;
    }

    public void setPriode(String priode) {
        this.priode = priode;
    }

    public String getTanggalKeberangkatan() {
        return tanggalKeberangkatan;
    }

    public void setTanggalKeberangkatan(String tanggalKeberangkatan) {
        this.tanggalKeberangkatan = tanggalKeberangkatan;
    }

    public String getTanggalKepulangan() {
        return tanggalKepulangan;
    }

    public void setTanggalKepulangan(String tanggalKepulangan) {
        this.tanggalKepulangan = tanggalKepulangan;
    }

    public String getJudulKegiatan() {
        return judulKegiatan;
    }

    public void setJudulKegiatan(String judulKegiatan) {
        this.judulKegiatan = judulKegiatan;
    }

    public String getDeskripsiKegiatan() {
        return deskripsiKegiatan;
    }

    public void setDeskripsiKegiatan(String deskripsiKegiatan) {
        this.deskripsiKegiatan = deskripsiKegiatan;
    }
}
