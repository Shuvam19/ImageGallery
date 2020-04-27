package com.example.imagegallery.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagegallery.Model.Photo;
import com.example.imagegallery.R;
import com.example.imagegallery.ViewModel.HomeViewModel;

public class HomeFragment extends Fragment {
    HomeViewModel homeViewModel;
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeAdapter = new HomeAdapter(getActivity());

        homeViewModel.getPagedListLiveData().observe(getActivity(), new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(PagedList<Photo> photos) {
                homeAdapter.submitList(photos);
            }
        });
        recyclerView.setAdapter(homeAdapter);
        return view;
    }

}
