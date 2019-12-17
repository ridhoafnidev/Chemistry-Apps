package com.example.appskimia.Response;

import com.example.appskimia.Model.Kegiatan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseKegiatan {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("kegiatan")
    @Expose
    private List<Kegiatan> kegiatan = null;

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

    public List<Kegiatan> getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(List<Kegiatan> kegiatan) {
        this.kegiatan = kegiatan;
    }
}
