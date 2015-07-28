package com.movile.up.seriestracker.loader;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

import com.movile.up.seriestracker.database.manual.dao.FavoriteDAO;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesLoader extends CursorLoader {

    private Context mContext;

    public FavoritesLoader(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    public Cursor loadInBackground() {
        FavoriteDAO favoriteDAO = new FavoriteDAO(mContext);
        return favoriteDAO.all();
    }
}
