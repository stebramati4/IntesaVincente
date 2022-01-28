package com.example.intesavincente.repository.words;

import android.app.Application;
import android.telecom.Call;

import com.example.intesavincente.model.WordsResponse;
import com.example.intesavincente.service.WordsApiService;
import com.example.intesavincente.utils.Constants;
import com.example.intesavincente.utils.ResponseCallback;
import com.example.intesavincente.utils.ServiceLocator;

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

    @Override
    public void fetchWords(String parola) {

        // It gets the news from the Web Service if the last download
        // of the news has been performed more than one minute ago
        if (currentTime - lastUpdate > Constants.FRESH_TIMEOUT) {
            Call<WordsResponse> wordsResponseCall = mWordsApiService.getWords(parola, Constants.API_KEY);
            //passo nazione e key->restituisce call che mi consente di fare la chiamata
            wordsResponseCall.enqueue(new Callback<WordsResponse>() { //queue per fare chiamata in background
                //ha bisogno di ogg di tipo callback (tipo della classe per mappatura del json che ritorna api
                //in post chiamata)

                @Override
                public void onResponse(@NonNull Call<WordsResponse> call, @NonNull Response<WordsResponse> response) {
                    //accedo alla callback di retrofit se chiamata andata a buon fine
                    if (response.body() != null && response.isSuccessful() && !response.body().getStatus().equals("error")) {
                        List<News> newsList = response.body().getArticles();
                        saveDataInDatabase(newsList);
                        mResponseCallback.onResponse(newsList, System.currentTimeMillis()); //andata a buon fine
                    } else {
                        mResponseCallback.onFailure(mApplication.getString(R.string.error_retrieving_news));
                    }
                }

                @Override
                public void onFailure(@NonNull Call<NewsResponse> call, @NonNull Throwable t) {
                    //se chiamata con retrofit va male
                    mResponseCallback.onFailure(t.getMessage());
                }
            });
        } else {
            Log.d(TAG, "Data read from the local database");
            readDataFromDatabase(lastUpdate);
        }
    }

}
