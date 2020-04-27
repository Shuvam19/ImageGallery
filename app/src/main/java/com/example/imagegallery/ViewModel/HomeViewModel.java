package com.example.imagegallery.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.imagegallery.Model.Photo;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class HomeViewModel extends AndroidViewModel {
    HomeDataSouceFactory homeDataSouceFactory;
    MutableLiveData<HomeDataSource> dataSourceMutableLiveData;
    Executor executor;
    LiveData<PagedList<Photo>> pagedListLiveData;
    public HomeViewModel(@NonNull Application application) {
        super(application);
        homeDataSouceFactory = new HomeDataSouceFactory();
        dataSourceMutableLiveData = homeDataSouceFactory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();
        executor = Executors.newFixedThreadPool(5);
        pagedListLiveData = (new LivePagedListBuilder<Integer,Photo>(homeDataSouceFactory,config))
                .setFetchExecutor(executor)
                .build();
    }
    public LiveData<PagedList<Photo>> getPagedListLiveData() {
        return pagedListLiveData;
    }
}
