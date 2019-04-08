package com.example.contentarticle.model.retrovit;


import com.google.gson.annotations.SerializedName;


public class ResponseRegister {

    @SerializedName("response")
    private String response;

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}