package com.movile.up.seriestracker.database.manual;

/**
 * Created by android on 7/27/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity;

public class SeriesTrackerDbHelper extends SQLiteOpenHelper {

    public SeriesTrackerDbHelper(Context context) {
        super(context, DatabaseConfiguration.NAME, null, DatabaseConfiguration.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(FavoriteEntity.FavoriteEntityFields.createSql());
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(FavoriteEntity.FavoriteEntityFields.dropSql());
        onCreate(database);
    }

}