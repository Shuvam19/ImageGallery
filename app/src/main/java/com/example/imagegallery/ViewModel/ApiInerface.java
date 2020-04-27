package com.example.imagegallery.ViewModel;

import com.example.imagegallery.Model.ApiResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInerface {
    String Base_URL="https://api.flickr.com/";

    @GET("services/rest/")
    Call<ApiResponse> getImages(
            @QueryMap Map<String,String> options
    );
}
