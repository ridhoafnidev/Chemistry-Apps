package com.example.appskimia.Response;

import com.example.appskimia.Model.Larangan;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseLarangan {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("larangan")
    @Expose
    private List<Larangan> larangan = null;

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

    public List<Larangan> getLarangan() {
        return larangan;
    }

    public void setLarangan(List<Larangan> larangan) {
        this.larangan = larangan;
    }
}
