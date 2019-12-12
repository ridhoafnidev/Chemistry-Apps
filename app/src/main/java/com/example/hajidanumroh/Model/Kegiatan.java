package com.example.hajidanumroh.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kegiatan {

    @SerializedName("id_kegiatan")
    @Expose
    private String idKegiatan;
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
