package com.sagar.statussaver.screens.videoplayer;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

@VideoPlayerActivityScope
public class VideoPlayerActivityViewModelFactory implements ViewModelProvider.Factory {


    @Inject
    VideoPlayerActivityViewModelFactory() {
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(VideoPlayerActivityViewModel.class)) {
            return (T) new VideoPlayerActivityViewModel();
        } else {
            throw new IllegalArgumentException("Can not find ViewModel class " + modelClass.getName());
        }
    }
}
//@ContributesAndroidInjector(modules = {VideoPlayerActivityModule.class})
//VideoPlayerActivityScope
//abstract VideoPlayerActivity bindVideoPlayerActivity();

