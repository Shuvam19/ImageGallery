package com.example.imagegallery.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.imagegallery.Model.ApiResponse;

import java.util.Map;

public class SearchViewModel extends ViewModel {
    private SearchRepo searchRepo;
    private Map<String,String> pass_value;
    private MutableLiveData<ApiResponse> liveData;

    public SearchViewModel(Map<String,String> pass_value) {
        searchRepo = new SearchRepo();
        this.pass_value = pass_value;
    }

    public LiveData<ApiResponse> getImages(){
        if(liveData==null){
            liveData = searchRepo.requestImages(pass_value);
        }
        return liveData;
    }
}
