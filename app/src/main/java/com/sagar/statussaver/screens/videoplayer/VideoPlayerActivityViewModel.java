package com.sagar.statussaver.screens.videoplayer;

import androidx.lifecycle.ViewModel;


import io.reactivex.disposables.CompositeDisposable;

public class VideoPlayerActivityViewModel extends ViewModel {


    private final CompositeDisposable disposable = new CompositeDisposable();

    private VideoPlayerActivityModel activityModel = new VideoPlayerActivityModel();
    ;

    public VideoPlayerActivityViewModel() {
    }


    public VideoPlayerActivityModel getActivityModel() {
        return activityModel;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.dispose();
    }
}
