package com.sagar.statussaver.screens.main;

import com.sagar.statussaver.screens.main.home.photos.PhotosFragment;
import com.sagar.statussaver.screens.main.home.photos.PhotosFragmentModule;
import com.sagar.statussaver.screens.main.home.photos.PhotosFragmentScope;
import com.sagar.statussaver.screens.main.home.videos.VideosFragment;
import com.sagar.statussaver.screens.main.home.videos.VideosFragmentModule;
import com.sagar.statussaver.screens.main.home.videos.VideosFragmentScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@SuppressWarnings("unused")
@Module
public abstract class MainActivityFragmentProvider {

    @ContributesAndroidInjector(modules = PhotosFragmentModule.class)
    @PhotosFragmentScope
    abstract PhotosFragment bindPhotosFragment();

    @ContributesAndroidInjector(modules = VideosFragmentModule.class)
    @VideosFragmentScope
    abstract VideosFragment bindVideosFragment();

}
