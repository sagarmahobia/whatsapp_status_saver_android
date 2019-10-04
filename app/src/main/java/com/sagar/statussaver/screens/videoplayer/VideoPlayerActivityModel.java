package com.sagar.statussaver.screens.videoplayer;

import androidx.databinding.BaseObservable;

public class VideoPlayerActivityModel extends BaseObservable {

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
