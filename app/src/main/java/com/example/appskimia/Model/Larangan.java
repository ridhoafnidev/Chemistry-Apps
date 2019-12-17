package com.example.appskimia.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Larangan {
    @SerializedName("id_larangan")
    @Expose
    private String idLarangan;
    @SerializedName("judul_larangan")
    @Expose
    private String judulLarangan;
    @SerializedName("deskripsi_larangan")
    @Expose
    private String deskripsiLarangan;

    public String getIdLarangan() {
        return idLarangan;
    }

    public void setIdLarangan(String idLarangan) {
        this.idLarangan = idLarangan;
    }

    public String getJudulLarangan() {
        return judulLarangan;
    }

    public void setJudulLarangan(String judulLarangan) {
        this.judulLarangan = judulLarangan;
    }

    public String getDeskripsiLarangan() {
        return deskripsiLarangan;
    }

    public void setDeskripsiLarangan(String deskripsiLarangan) {
        this.deskripsiLarangan = deskripsiLarangan;
    }
}
