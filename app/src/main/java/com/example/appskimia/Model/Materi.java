package com.example.appskimia.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Materi {
    @SerializedName("id_materi")
    @Expose
    private String idMateri;
    @SerializedName("jenis_materi")
    @Expose
    private String jenisMateri;
    @SerializedName("judul_materi")
    @Expose
    private String judulMateri;
    @SerializedName("deskripsi_materi")
    @Expose
    private String deskripsiMateri;
    @SerializedName("link_youtube")
    @Expose
    private String linkYoutube;

    public String getIdMateri() {
        return idMateri;
    }

    public void setIdMateri(String idMateri) {
        this.idMateri = idMateri;
    }

    public String getJenisMateri() {
        return jenisMateri;
    }

    public void setJenisMateri(String jenisMateri) {
        this.jenisMateri = jenisMateri;
    }

    public String getJudulMateri() {
        return judulMateri;
    }

    public void setJudulMateri(String judulMateri) {
        this.judulMateri = judulMateri;
    }

    public String getDeskripsiMateri() {
        return deskripsiMateri;
    }

    public void setDeskripsiMateri(String deskripsiMateri) {
        this.deskripsiMateri = deskripsiMateri;
    }

    public String getLinkYoutube() {
        return linkYoutube;
    }

    public void setLinkYoutube(String linkYoutube) {
        this.linkYoutube = linkYoutube;
    }
}
