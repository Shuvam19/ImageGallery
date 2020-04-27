package com.example.imagegallery.ViewModel;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.paging.PageKeyedDataSource;

import com.example.imagegallery.Model.ApiResponse;
import com.example.imagegallery.Model.Photo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeDataSource extends PageKeyedDataSource<Long, Photo> {
    ApiInerface dataService;
    Map<String,String> map = new HashMap<>();

    public HomeDataSource() {
        this.map.put("method","flickr.photos.getRecent");
        this.map.put("page","1");
        this.map.put("per_page","20");
        this.map.put("api_key","6f102c62f41998d151e5a1b48713cf13");
        this.map.put("format","json");
        this.map.put("nojsoncallback","1");
        this.map.put("extras","url_s");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Photo> callback) {
        map.replace("page","1");
        dataService = RetrofitClientInstance.getRetrofitInstance().create(ApiInerface.class);
        Call<ApiResponse> data = dataService.getImages(map);
        data.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.e("msg",response.body().getPhotos().getPhoto().get(0).getId());
                List<Photo> photosList = response.body().getPhotos().getPhoto();
                callback.onResult( photosList,null,(long)2);
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long,Photo> callback) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Photo> callback) {
        map.replace("page", String.valueOf(params.key+1));
        Log.i("ans", String.valueOf(params.key+1));
        dataService = RetrofitClientInstance.getRetrofitInstance().create(ApiInerface.class);
        Call<ApiResponse> data = dataService.getImages(map);
        data.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<Photo> photosList = response.body().getPhotos().getPhoto();
                callback.onResult( photosList, (long)(params.key+1));
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });


    }
}
