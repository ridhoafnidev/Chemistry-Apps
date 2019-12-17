package com.example.appskimia.Response;

import com.example.appskimia.Model.Doa;
import com.example.appskimia.Model.Materi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseDoa {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("doa")
    @Expose
    private List<Doa> doa = null;

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

    public List<Doa> getDoa() {
        return doa;
    }

    public void setDoa(List<Doa> doa) {
        this.doa = doa;
    }
}
