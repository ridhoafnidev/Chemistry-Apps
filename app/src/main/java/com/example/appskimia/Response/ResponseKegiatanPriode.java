package com.example.appskimia.Response;

import com.example.appskimia.Model.KegiatanPriode;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKegiatanPriode {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("kegiatan")
    @Expose
    private List<KegiatanPriode> kegiatan = null;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<KegiatanPriode> getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(List<KegiatanPriode> kegiatan) {
        this.kegiatan = kegiatan;
    }

}
