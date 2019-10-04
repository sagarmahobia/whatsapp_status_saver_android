package com.sagar.statussaver.screens.main.home;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
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

    private FragmentHomeBinding binding;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());

        viewPagerAdapter.addFragment(new PhotosFragment(), "Photos");
        viewPagerAdapter.addFragment(new VideosFragment(), "Videos");

        binding.tabLayout.setupWithViewPager(binding.pager);
        binding.pager.setAdapter(viewPagerAdapter);

        return binding.getRoot();
    }

}
