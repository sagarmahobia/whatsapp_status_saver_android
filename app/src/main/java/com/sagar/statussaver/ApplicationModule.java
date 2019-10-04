package com.sagar.statussaver;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    @ApplicationScope
    Context provideContext(Application application) {
        return application.getBaseContext();
    }

    @Provides
    @ApplicationScope
    Picasso providePicasso(Context context) {
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(context, Integer.MAX_VALUE))
                .addRequestHandler(new RequestHandler() {
                    private String SCHEME_VIDEO = "video";

                    @Override
                    public boolean canHandleRequest(Request data) {
                        String scheme = data.uri.getScheme();
                        return (SCHEME_VIDEO.equals(scheme));
                    }

                    @Override
                    public Result load(Request data, int arg1) {
                        Bitmap bm = ThumbnailUtils.createVideoThumbnail(data.uri.getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
                        return new Result(bm, Picasso.LoadedFrom.DISK);
                    }
                })
                .build();
        if (BuildConfig.DEBUG) {
            picasso.setIndicatorsEnabled(true);
            picasso.setLoggingEnabled(true);
        }
        return picasso;
    }
}
