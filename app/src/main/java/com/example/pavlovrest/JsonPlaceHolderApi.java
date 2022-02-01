package com.example.pavlovrest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("/reactResistInd")
     Call<Post> getReactResistInd(@Query("freq") int freq, @Query("ind") int ind);
}
