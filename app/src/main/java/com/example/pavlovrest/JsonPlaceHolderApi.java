package com.example.pavlovrest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("/reactResistInd")
    Call<Post> getReactResistInd(@Query("freq") int freq, @Query("freqMul") int fMul, @Query("ind") int ind, @Query("indMul") int iMul);

    @GET("/reactResistCap")
    Call<Post> getReactResistCap(@Query("freq") int freq, @Query("freqMul") int fMul, @Query("cap") int cap, @Query("capMul") int iMul);
}
