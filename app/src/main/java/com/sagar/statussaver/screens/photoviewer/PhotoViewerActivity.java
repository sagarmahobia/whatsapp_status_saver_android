package com.sagar.statussaver.screens.photoviewer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.sagar.statussaver.databinding.ActivityPhotoViewerBinding;
import com.squareup.picasso.Picasso;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class PhotoViewerActivity extends AppCompatActivity implements PhotoViewerHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityPhotoViewerBinding binding = ActivityPhotoViewerBinding.inflate(getLayoutInflater());

        binding.setHandler(this);
        setContentView(binding.getRoot());


        String path = getIntent().getStringExtra("path");

        Picasso.get().load(new File(path)).into(binding.imageView);
//        imageView.setImageURI(Uri.fromFile(new File(path)));

    }

    @Override
    public void save() {

        String sourcePath = getIntent().getStringExtra("path");
        File source = new File(sourcePath);

        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/StatusSaver/Photos/" + source.getName();
        File destination = new File(destinationPath);

        if (destination.exists()) {
            Toast.makeText(this, "Already Saved", Toast.LENGTH_SHORT).show();
            return;
        }

        if (savePhoto()) {
            Toast.makeText(this, "Saved to /StatusSaver/Photos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to save photo. Try again.", Toast.LENGTH_SHORT).show();
            this.finish();
        }

    }

    @Override
    public void share() {
        String sourcePath = getIntent().getStringExtra("path");
        File source = new File(sourcePath);

        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/StatusSaver/Photos/" + source.getName();
        File destination = new File(destinationPath);

        if (!destination.exists()) {
            if (!savePhoto()) {
                Toast.makeText(this, "Can not share photo. Please try again.", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Uri videoUri = FileProvider.getUriForFile(this, getPackageName() + ".fileprovider", destination);

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpg");
        share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        share.putExtra(Intent.EXTRA_STREAM, videoUri);

        startActivity(share);


    }

    private boolean savePhoto() {
        String sourcePath = getIntent().getStringExtra("path");
        File source = new File(sourcePath);

        String destinationPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/StatusSaver/Photos/" + source.getName();
        File destination = new File(destinationPath);

        try {
            FileUtils.copyFile(source, destination);
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
