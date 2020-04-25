package com.example.imagegallery.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.imagegallery.Model.ApiResponse;
import com.example.imagegallery.R;
import com.example.imagegallery.ViewModel.ImageViewModel;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ImagesAdapter imagesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ImageViewModel imageViewModel = new ImageViewModel();

        imageViewModel.getImages().observe(getActivity(), new Observer<ApiResponse>() {

            @Override
            public void onChanged(ApiResponse apiResponse) {
                imagesAdapter = new ImagesAdapter(getContext(), apiResponse);
                recyclerView.setAdapter(imagesAdapter);
            }
        });
        return view;
    }

}
