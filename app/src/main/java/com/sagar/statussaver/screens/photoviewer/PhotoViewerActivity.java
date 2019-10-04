package com.sagar.statussaver.screens.photoviewer;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.sagar.statussaver.R;
import com.squareup.picasso.Picasso;

import java.io.File;

public class PhotoViewerActivity extends AppCompatActivity implements PhotoViewerHandler {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_viewer);

        ImageView imageView = findViewById(R.id.image_view);

        String path = getIntent().getStringExtra("path");

        Picasso.get().load(new File(path)).into(imageView);
//        imageView.setImageURI(Uri.fromFile(new File(path)));


    }

    @Override
    public void save() {
        
    }

    @Override
    public void share() {

    }
}
