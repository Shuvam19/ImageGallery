package com.example.imagegallery.ViewModel;

import com.example.imagegallery.Model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInerface {
    String Base_URL="https://api.flickr.com/";

    @GET("services/rest/?method=flickr.photos.getRecent&per_page=20&page=1&api_key=6f102c62f41998d151e5a1b48713cf13&format=json&nojsoncallback=1&extras=url_s")
    Call<ApiResponse> getImages();
}
