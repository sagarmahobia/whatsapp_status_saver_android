package com.sagar.statussaver.screens.videoplayer;

import androidx.lifecycle.ViewModel;

public class VideoPlayerActivityViewModel extends ViewModel {
    private VideoPlayerActivityModel activityModel = new VideoPlayerActivityModel();

    public VideoPlayerActivityViewModel() {
    }


    public VideoPlayerActivityModel getActivityModel() {
        return activityModel;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
