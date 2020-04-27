package com.example.imagegallery.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.imagegallery.Model.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRepo {

    public MutableLiveData<ApiResponse> requestImages(Map<String,String> pass_value) {
        final MutableLiveData<ApiResponse> liveData = new MutableLiveData<>();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiInerface.Base_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();

        ApiInerface api = retrofit.create(ApiInerface.class);
        Call<ApiResponse> call = api.getImages(pass_value);

        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.i("msg",response.toString());
                liveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.i("err",t.toString());
            }
        });
        return liveData;
    }
}
