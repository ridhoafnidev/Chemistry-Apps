package com.example.hajidanumroh.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Doa {
    @SerializedName("id_doa")
    @Expose
    private String idDoa;
    @SerializedName("judul_doa")
    @Expose
    private String judulDoa;
    @SerializedName("gambar_doa")
    @Expose
    private String gambarDoa;
    @SerializedName("deskripsi_doa")
    @Expose
    private String deskripsiDoa;

    public String getIdDoa() {
        return idDoa;
    }

    public void setIdDoa(String idDoa) {
        this.idDoa = idDoa;
    }

    public String getJudulDoa() {
        return judulDoa;
    }

    public void setJudulDoa(String judulDoa) {
        this.judulDoa = judulDoa;
    }

    public String getGambarDoa() {
        return gambarDoa;
    }

    public void setGambarDoa(String gambarDoa) {
        this.gambarDoa = gambarDoa;
    }

    public String getDeskripsiDoa() {
        return deskripsiDoa;
    }

    public void setDeskripsiDoa(String deskripsiDoa) {
        this.deskripsiDoa = deskripsiDoa;
    }
}
