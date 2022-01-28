package com.example.intesavincente.utils;

public interface ResponseCallback {

    void onResponse(String parola);
    void onFailure(String errorMessage);

}
