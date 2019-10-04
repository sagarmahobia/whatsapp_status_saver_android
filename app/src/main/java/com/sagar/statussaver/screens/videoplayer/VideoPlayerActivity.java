
package com.sagar.statussaver.screens.videoplayer;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.sagar.statussaver.R;
import com.sagar.statussaver.databinding.ActivityVideoPlayerBinding;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class VideoPlayerActivity extends AppCompatActivity implements VideoPlayerActivityHandler {

    private ActivityVideoPlayerBinding binding;

    private VideoPlayerActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_video_player);
        binding.setHandler(this);


        viewModel = ViewModelProviders.of(this).get(VideoPlayerActivityViewModel.class);

        String path = getIntent().getStringExtra("path");

        viewModel.getActivityModel().setPath(path);

        binding.player.setSource(path);

        binding.player.setExoPlayerCallBack(() -> {
            this.finish();
            Toast.makeText(this, "Failed to play video please try again", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public void save() {

    }

    @Override
    public void share() {

    }

}
