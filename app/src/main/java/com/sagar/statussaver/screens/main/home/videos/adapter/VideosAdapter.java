package com.sagar.statussaver.screens.main.home.videos.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sagar.statussaver.R;
import com.sagar.statussaver.databinding.ListVideoItemHolderBinding;
import com.sagar.statussaver.screens.main.home.videos.VideosFragmentScope;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;


@VideosFragmentScope
public class VideosAdapter extends RecyclerView.Adapter<VideoViewHolder> {

    private OnItemClickListener onItemClickListener;

    private List<VideoModel> videoModels;

    private LayoutInflater inflater;

    private Picasso picasso;

    @Inject
    public VideosAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    public void setVideoModels(List<VideoModel> videoModels) {
        this.videoModels = videoModels;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent.getContext());
        }
        ListVideoItemHolderBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_video_item_holder, parent, false);
        return new VideoViewHolder(binding, picasso);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoModel videoModel = videoModels.get(position);
        holder.bindTo(videoModel);
        holder.getView().setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onClick(videoModel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoModels != null ? videoModels.size() : 0;
    }

    public interface OnItemClickListener {
        void onClick(VideoModel videoModel);
    }

}
