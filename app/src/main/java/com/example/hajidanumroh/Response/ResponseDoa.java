package com.example.hajidanumroh.Response;

import com.example.hajidanumroh.Model.Doa;
import com.example.hajidanumroh.Model.Materi;
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
