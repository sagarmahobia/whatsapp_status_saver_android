
package com.sagar.statussaver.screens.videoplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.sagar.statussaver.R;
import com.sagar.statussaver.databinding.ActivityVideoPlayerBinding;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

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
    protected void onStop() {
        binding.player.pausePlayer();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        binding.player.stopPlayer();
        super.onDestroy();
    }

    @Override
    public void save() {

        File source = new File(viewModel.getActivityModel().getPath());

        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/StatusSaver/Videos/" + source.getName();
        File destination = new File(destinationPath);

        if (destination.exists()) {
            Toast.makeText(this, "Already Saved", Toast.LENGTH_SHORT).show();
            return;
        }

        if (saveVideo()) {
            Toast.makeText(this, "Saved to /StatusSaver/Videos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save video. Try again.", Toast.LENGTH_SHORT).show();
            this.finish();
        }

    }

    @Override
    public void share() {

        File source = new File(viewModel.getActivityModel().getPath());

        String destinationPath = Environment
                .getExternalStorageDirectory()
                .getAbsolutePath() + "/StatusSaver/Videos/" + source.getName();
        File destination = new File(destinationPath);

        if (!destination.exists()) {
            if (!saveVideo()) {
                Toast.makeText(this, "Can not share video. Please try again.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Uri videoUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", destination);

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("video/mp4");
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        share.putExtra(Intent.EXTRA_STREAM, videoUri);

        startActivity(share);
    }


    private boolean saveVideo() {
        File source = new File(viewModel.getActivityModel().getPath());

        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/StatusSaver/Videos/" + source.getName();
        File destination = new File(destinationPath);

        try {
            FileUtils.copyFile(source, destination);
            return true;
        } catch (IOException e) {
            return false;
        }

    }

}
