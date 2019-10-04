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


    @Inject
    VideosAdapter adapter;

    private VideosFragmentVideoModel viewModel;

    private FragmentVideosBinding binding;

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
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
    public void onStart() {
        super.onStart();
        viewModel.load();
    }

    private void observeFileResponse(Response<List<VideoModel>> response) {
        adapter.setVideoModels(response.getData());
    }
}
