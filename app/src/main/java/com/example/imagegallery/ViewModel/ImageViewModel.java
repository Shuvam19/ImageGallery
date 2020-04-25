package com.example.imagegallery.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.imagegallery.Model.ApiResponse;

public class ImageViewModel extends ViewModel {
    private ImageRepo imageRepo;

    private MutableLiveData<ApiResponse> liveData;

    public ImageViewModel() {
        imageRepo = new ImageRepo();
    }

    public LiveData<ApiResponse> getImages(){
        if(liveData==null){
            liveData = imageRepo.requestImages();
        }
        return liveData;
    }
}
