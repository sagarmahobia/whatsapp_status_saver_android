package com.sagar.statussaver.screens.main.home.photos;


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
import com.sagar.statussaver.databinding.FragmentPhotosBinding;
import com.sagar.statussaver.responsewrappers.Response;
import com.sagar.statussaver.responsewrappers.Status;
import com.sagar.statussaver.screens.main.home.photos.adapter.PhotoModel;
import com.sagar.statussaver.screens.main.home.photos.adapter.PhotosAdapter;
import com.sagar.statussaver.screens.photoviewer.PhotoViewerActivity;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotosFragment extends Fragment {

    @Inject
    PhotosAdapter adapter;

    private PhotosFragmentViewModel viewModel;
    private FragmentPhotosBinding binding;
    private String type;

    public static PhotosFragment getInstance(String type) {
        PhotosFragment photosFragment = new PhotosFragment();
        photosFragment.type = type;
        return photosFragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        viewModel = ViewModelProviders.of(this).get(PhotosFragmentViewModel.class);

        viewModel.getFileResponseModel().observe(this, this::observeFileResponse);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_photos, container, false);
        binding.recycler.setAdapter(adapter);

        adapter.setOnItemClickListener(photoModel -> {
            Intent intent = new Intent(this.getContext(), PhotoViewerActivity.class);
            intent.putExtra("path", photoModel.getFile().getAbsolutePath());
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
            viewModel.load("/StatusSaver/Photos");
        }
    }

    private void observeFileResponse(Response<List<PhotoModel>> response) {
        if (response.getStatus() == Status.ERROR) {
            return;
        }
        if (response.getData().isEmpty()) {

            binding.noStatus.setVisibility(View.VISIBLE);
            binding.recycler.setVisibility(View.GONE);

        } else {
            adapter.setPhotoModels(response.getData());

            binding.noStatus.setVisibility(View.GONE);
            binding.recycler.setVisibility(View.VISIBLE);

        }
    }

}
