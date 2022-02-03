package com.example.pavlovrest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private static NetworkService mInstance;
//    private static final String BASE_URL = "https://e58e-128-75-140-209.ngrok.io";
private static final String BASE_URL = "https://2ed5-128-75-140-209.ngrok.io";
    private Retrofit mRetrofit;

    private NetworkService() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkService();
        }
        return mInstance;
    }

    public JsonPlaceHolderApi getJsonApi(){
        return mRetrofit.create(JsonPlaceHolderApi.class);
    }
}
