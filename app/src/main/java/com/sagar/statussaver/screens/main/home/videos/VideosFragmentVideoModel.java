package com.sagar.statussaver.screens.main.home.videos;

import android.os.Environment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sagar.statussaver.responsewrappers.Response;
import com.sagar.statussaver.screens.main.home.videos.adapter.VideoModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideosFragmentVideoModel extends ViewModel {

    private MutableLiveData<Response> fileResponseModel = new MutableLiveData<>();

    public VideosFragmentVideoModel() {
    }

    MutableLiveData<Response> getFileResponseModel() {
        return fileResponseModel;
    }

    void load(String location) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + location;

        File file = new File(path);
        if (!file.exists()) {
            fileResponseModel.setValue(Response.success(new ArrayList<>()));
            return;
        }
        List<String> files = new ArrayList<>();

        String[] list = file.list();

        if (list == null) {
            fileResponseModel.setValue(Response.success(new ArrayList<>()));
            return;
        }

        for (String fileName : list) {
            if (fileName.endsWith(".mp4")) {
                files.add(path + "/" + fileName);
            }
        }


        List<VideoModel> videoModels = new ArrayList<>();

        for (String imageFile : files) {
            VideoModel videoModel = new VideoModel();
            videoModel.setFile(new File(imageFile));
            videoModels.add(videoModel);
        }

        Collections.sort(videoModels, (o1, o2) -> Long.compare(o2.getFile().lastModified(), o1.getFile().lastModified()));
        fileResponseModel.setValue(Response.success(videoModels));
    }


}
