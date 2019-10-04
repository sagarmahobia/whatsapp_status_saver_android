package com.sagar.statussaver.screens.main.home.videos.adapter;

import androidx.databinding.BaseObservable;

import java.io.File;

public class VideoModel extends BaseObservable {

    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
