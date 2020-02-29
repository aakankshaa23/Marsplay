package com.example.marsplay.interfaces;

import com.example.marsplay.Models.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface interf {

    String BASE_URL="http://api.plos.org/";
    @GET("search?q=title:DNA")
    Call<Model> getmodel();



}
