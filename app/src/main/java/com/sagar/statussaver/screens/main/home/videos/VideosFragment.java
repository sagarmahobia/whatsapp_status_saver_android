package com.sagar.statussaver.screens.main.home.videos;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.sagar.statussaver.R;
import com.sagar.statussaver.databinding.FragmentVideosBinding;
import com.sagar.statussaver.responsewrappers.Response;
import com.sagar.statussaver.responsewrappers.Status;
import com.sagar.statussaver.screens.main.home.videos.adapter.VideoModel;
import com.sagar.statussaver.screens.main.home.videos.adapter.VideosAdapter;
import com.sagar.statussaver.screens.videoplayer.VideoPlayerActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {

    private String type;

    @Inject
    VideosAdapter adapter;

    private VideosFragmentVideoModel viewModel;

    private FragmentVideosBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public static VideosFragment getInstance(String type) {
        VideosFragment videosFragment = new VideosFragment();
        videosFragment.type = type;
        return videosFragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewModel = ViewModelProviders.of(this).get(VideosFragmentVideoModel.class);

        viewModel.getFileResponseModel().observe(this, this::observeFileResponse);


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_videos, container, false);

        binding.recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(videoModel -> {
            Intent intent = new Intent(this.getContext(), VideoPlayerActivity.class);
            intent.putExtra("path", videoModel.getFile().getAbsolutePath());
            startActivity(intent);
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (type.equalsIgnoreCase("whatsapp")) {
            viewModel.load("/WhatsApp/Media/.Statuses");
        } else if (type.equalsIgnoreCase("saved")) {
            viewModel.load("/StatusSaver/Videos");
        }
    }

    private void observeFileResponse(Response<List<VideoModel>> response) {
        if (response.getStatus() == Status.ERROR) {
            return;
        }
        if (response.getData().isEmpty()) {

            binding.noStatus.setVisibility(View.VISIBLE);
            binding.recycler.setVisibility(View.GONE);

        } else {
            adapter.setVideoModels(response.getData());

            binding.noStatus.setVisibility(View.GONE);
            binding.recycler.setVisibility(View.VISIBLE);

        }

    }
}
