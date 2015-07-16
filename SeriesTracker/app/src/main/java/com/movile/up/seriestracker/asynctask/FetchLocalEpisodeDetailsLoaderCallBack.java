package com.movile.up.seriestracker.asynctask;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.up.seriestracker.interfaces.OnEpisodeDetailsListener;
import com.movile.up.seriestracker.model.Episode;

import java.net.URL;


/**
 * Created by android on 7/16/15.
 */
public class FetchLocalEpisodeDetailsLoaderCallBack implements LoaderManager.LoaderCallbacks<Episode>{
    private Context mContext;
    private String mUrl;
    private OnEpisodeDetailsListener<Episode> mListener;

    public FetchLocalEpisodeDetailsLoaderCallBack(Context context, OnEpisodeDetailsListener<Episode> listener, String url){
        mContext = context;
        mUrl = url;
        mListener = listener;
    }

    @Override
    public Loader<Episode> onCreateLoader(int id, Bundle args) {
        return new FetchLocalEpisodeDetailsLoader(mContext, mUrl);
    }

    @Override
    public void onLoadFinished(Loader<Episode> loader, Episode episode) {
        mListener.OnEpisodeDetailsSucess(episode);
    }

    @Override
    public void onLoaderReset(Loader<Episode> loader) {

    }
}
