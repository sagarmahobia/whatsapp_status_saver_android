package com.sagar.statussaver.screens.main.home.photos.adapter;

import androidx.databinding.BaseObservable;

import java.io.File;

public class PhotoModel extends BaseObservable {

    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }
}
