package com.movile.up.seriestracker.loader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.movile.up.seriestracker.interfaces.OnFavoritesLoaderListener;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private OnFavoritesLoaderListener mListener;

    public FavoritesLoaderCallback(Context context, OnFavoritesLoaderListener listener) {
        mContext = context;
        mListener = listener;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new FavoritesLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mListener.onFavoritesSuccess(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
