package com.example.intesavincente.repository.words;

import android.app.Application;
import android.os.Build;
import retrofit2.Call;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.intesavincente.R;
import com.example.intesavincente.model.WordsResponse;
import com.example.intesavincente.service.WordsApiService;
import com.example.intesavincente.utils.Constants;
import com.example.intesavincente.utils.ResponseCallback;
import com.example.intesavincente.utils.ServiceLocator;
import retrofit2.Callback;
import retrofit2.Response;

public class WordsRepository implements IWordsRepository{

    private static final String TAG = "WordsRepository";

    private final Application mApplication;
    private final WordsApiService mWordsApiService;
    private final ResponseCallback mResponseCallback;

    public WordsRepository(Application application, ResponseCallback responseCallback) {
        this.mApplication = application;
        this.mWordsApiService = ServiceLocator.getInstance().getWordsApiService(); //restituisce cleint retrofit
        this.mResponseCallback = responseCallback;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void fetchWords() {
        Call<WordsResponse> mWordsResponse = mWordsApiService.getWords();
        mWordsResponse.enqueue(new Callback<WordsResponse>() {
            @Override
            public void onResponse(Call<WordsResponse> call, Response<WordsResponse> response) {
                if (response.body() != null && response.isSuccessful() && !response.body().getParola().equals("error"))
                    //String parola = response.body().toString();
                    System.out.println("parola"+ response.toString());
                    System.out.println("parola1"+ response.body().getParola());
            }

            @Override
            public void onFailure(Call<WordsResponse> call, Throwable t) {

            }
        });
    }
}

