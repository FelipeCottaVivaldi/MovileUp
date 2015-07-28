package com.movile.up.seriestracker.database.manual.dao;

/**
 * Created by android on 7/27/15.
 */

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity;
import com.movile.up.seriestracker.database.manual.entity.FavoriteEntity$Table;
import com.movile.up.seriestracker.database.manual.helper.ProviderUriHelper;
import com.movile.up.seriestracker.model.Favorite;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;

public class FavoriteDAO {

    private Context mContext;

    public FavoriteDAO(Context context) {
        mContext = context;
    }

    public void save(Favorite favorite) {

        FavoriteEntity entity = new FavoriteEntity(favorite.slug(), favorite.title());
        entity.save();
    }

    public Cursor all() {
        return new Select().from(FavoriteEntity.class).queryCursorList().getCursor();
    }

    public Favorite query(String slug) {
        FavoriteEntity favoriteEntity = new Select()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .querySingle();


        if (favoriteEntity != null) {
            Favorite favorite = new Favorite(favoriteEntity.slug(), favoriteEntity.title());
            return favorite;
        } else return null;
    }

    public void delete(String slug) {
        new Delete()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .queryClose();
    }

}