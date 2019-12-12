package com.example.hajidanumroh.Response;

import com.example.hajidanumroh.Model.Materi;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseMateri {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("materi")
    @Expose
    private List<Materi> materi = null;

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

    public List<Materi> getMateri() {
        return materi;
    }

    public void setMateri(List<Materi> materi) {
        this.materi = materi;
    }
}
