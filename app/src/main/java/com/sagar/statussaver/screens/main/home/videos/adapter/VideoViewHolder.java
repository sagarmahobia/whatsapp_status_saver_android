package com.sagar.statussaver.screens.main.home.videos.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.statussaver.databinding.ListVideoItemHolderBinding;
import com.squareup.picasso.Picasso;

class VideoViewHolder extends RecyclerView.ViewHolder {

    private ListVideoItemHolderBinding binding;
    private Picasso picasso;

    VideoViewHolder(@NonNull ListVideoItemHolderBinding binding, Picasso picasso) {
        super(binding.getRoot());
        this.binding = binding;
        this.picasso = picasso;
    }

    void bindTo(VideoModel videoModel) {
        binding.setModel(videoModel);

//        Bitmap bm = ThumbnailUtils.createVideoThumbnail(videoModel.getFile().getPath(), MediaStore.Images.Thumbnails.MICRO_KIND);

        picasso.load("video:" + videoModel.getFile().getPath()).into(binding.image);
    }

    View getView() {
        return binding.getRoot();
    }
}
