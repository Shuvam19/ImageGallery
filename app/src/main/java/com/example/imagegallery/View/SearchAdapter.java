package com.example.imagegallery.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.imagegallery.Model.ApiResponse;
import com.example.imagegallery.Model.Photo;
import com.example.imagegallery.R;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ImageViewHolder> {

    private Context context;
    private ApiResponse apiResponse;

    public SearchAdapter(Context context, ApiResponse apiResponse) {
        this.context = context;
        this.apiResponse = apiResponse;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.images_recycler_view, viewGroup, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder imageViewHolder, int i) {
       Photo images = apiResponse.getPhotos().getPhoto().get(i);
        Glide.with(context).load(images.getUrlS()).into(imageViewHolder.image);
        imageViewHolder.text.setText(images.getTitle());
    }

    @Override
    public int getItemCount() {
        return apiResponse.getPhotos().getPhoto().size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView text;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);

        }
    }
}
