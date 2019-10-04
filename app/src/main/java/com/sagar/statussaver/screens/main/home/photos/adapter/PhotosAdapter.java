package com.sagar.statussaver.screens.main.home.photos.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.statussaver.R;
import com.sagar.statussaver.databinding.ListPhotoItemHolderBinding;
import com.sagar.statussaver.screens.main.home.photos.PhotosFragmentScope;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;


@PhotosFragmentScope
public class PhotosAdapter extends RecyclerView.Adapter<PhotoViewHolder> {

    private OnItemClickListener onItemClickListener;

    private List<PhotoModel> photoModels;

    private LayoutInflater inflater;

    private Picasso picasso;

    @Inject
    PhotosAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setPhotoModels(List<PhotoModel> photoModels) {
        this.photoModels = photoModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ListPhotoItemHolderBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_photo_item_holder, parent, false);
        return new PhotoViewHolder(binding, picasso);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        PhotoModel photoModel = photoModels.get(position);
        holder.bindTo(photoModel);
        holder.getView().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(photoModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoModels != null ? photoModels.size() : 0;
    }

    public interface OnItemClickListener {
        void onClick(PhotoModel photoModel);
    }
}
