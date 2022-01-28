package com.example.intesavincente.service;

import android.telecom.Call;

import com.example.intesavincente.model.WordsResponse;
import com.example.intesavincente.utils.Constants;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface WordsApiService {

    @GET(Constants.TOP_HEADLINES_ENDPOINT)
    Call<WordsResponse> getWords();

}
