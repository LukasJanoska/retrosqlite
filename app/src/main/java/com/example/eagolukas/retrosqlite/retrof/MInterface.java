package com.example.eagolukas.retrosqlite.retrof;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MInterface {
    @GET("/posts/5")
    Call<Post> getUser();
}
