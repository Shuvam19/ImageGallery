package com.example.imagegallery.ViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class HomeDataSouceFactory extends DataSource.Factory {
    HomeDataSource homeDataSource;
    MutableLiveData<HomeDataSource> mutableLiveData;

    public HomeDataSouceFactory() {
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        homeDataSource = new HomeDataSource();
        mutableLiveData.postValue(homeDataSource);
        return homeDataSource;
    }

    public MutableLiveData<HomeDataSource> getMutableLiveData(){
        return mutableLiveData;
    }
}
