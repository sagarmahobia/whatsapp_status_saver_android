package com.sagar.statussaver.screens.main.home.photos.adapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.statussaver.databinding.ListPhotoItemHolderBinding;
import com.squareup.picasso.Picasso;

class PhotoViewHolder extends RecyclerView.ViewHolder {

    private ListPhotoItemHolderBinding binding;
    private Picasso picasso;

    PhotoViewHolder(@NonNull ListPhotoItemHolderBinding binding, Picasso picasso) {
        super(binding.getRoot());
        this.binding = binding;
        this.picasso = picasso;
    }

    void bindTo(PhotoModel photoModel) {
        binding.setModel(photoModel);
        picasso.load(photoModel.getFile()).into(binding.image);
    }

    View getView() {
        return binding.getRoot();
    }
}
