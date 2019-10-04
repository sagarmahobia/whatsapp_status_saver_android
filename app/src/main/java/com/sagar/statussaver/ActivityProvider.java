package com.sagar.statussaver;

import com.sagar.statussaver.screens.main.MainActivity;
import com.sagar.statussaver.screens.main.MainActivityFragmentProvider;
import com.sagar.statussaver.screens.main.MainActivityModule;
import com.sagar.statussaver.screens.main.MainActivityScope;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityProvider {

    @ContributesAndroidInjector(modules = {MainActivityModule.class, MainActivityFragmentProvider.class})
    @MainActivityScope
    abstract MainActivity bindMainActivity();

}
