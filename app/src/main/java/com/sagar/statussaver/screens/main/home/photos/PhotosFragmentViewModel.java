package com.sagar.statussaver.screens.main.home.photos;

import android.os.Environment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sagar.statussaver.responsewrappers.Response;
import com.sagar.statussaver.screens.main.home.photos.adapter.PhotoModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhotosFragmentViewModel extends ViewModel {

    private MutableLiveData<Response> fileResponseModel = new MutableLiveData<>();

    public PhotosFragmentViewModel() {
        load();
    }

    MutableLiveData<Response> getFileResponseModel() {
        return fileResponseModel;
    }

    void load() {
        String path = Environment.getExternalStorageDirectory().toString() + "/WhatsApp/Media/.Statuses";
        File file = new File(path);

        List<String> files = new ArrayList<>();

        for (String fileName : file.list()) {
            if (fileName.endsWith(".jpg")) {
                files.add(path + "/" + fileName);
            }
        }


        List<PhotoModel> photoModels = new ArrayList<>();

        for (String imageFile : files) {
            PhotoModel videoModel = new PhotoModel();
            videoModel.setFile(new File(imageFile));
            photoModels.add(videoModel);
        }

        Collections.sort(photoModels, (o1, o2) -> Long.compare(o2.getFile().lastModified(), o1.getFile().lastModified()));
        fileResponseModel.setValue(Response.success(photoModels));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
