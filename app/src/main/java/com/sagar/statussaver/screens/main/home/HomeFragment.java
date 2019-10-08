package com.sagar.statussaver.screens.main.home;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sagar.statussaver.R;
import com.sagar.statussaver.adapters.viewpager.ViewPagerAdapter;
import com.sagar.statussaver.databinding.FragmentHomeBinding;
import com.sagar.statussaver.screens.main.home.photos.PhotosFragment;
import com.sagar.statussaver.screens.main.home.videos.VideosFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 100;

    private FragmentHomeBinding binding;
    private Activity activity;

    @Override
    public void onAttach(@NonNull Context context) {
        this.activity = ((Activity) context);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            requestPermissions(
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        } else {
            init();
        }

        return binding.getRoot();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                init();
            } else {
                Toast.makeText(this.getContext(), "Storage permission is required.", Toast.LENGTH_SHORT).show();
                activity.finish();
            }
        }
    }

    private void init() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        Bundle arguments = getArguments();
        String type = arguments.getString("type", "whatsapp");
        viewPagerAdapter.addFragment(PhotosFragment.getInstance(type), "Photos");
        viewPagerAdapter.addFragment(VideosFragment.getInstance(type), "Videos");

        binding.tabLayout.setupWithViewPager(binding.pager);
        binding.pager.setAdapter(viewPagerAdapter);

    }
}
