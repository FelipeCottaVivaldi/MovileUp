package com.movile.up.seriestracker.database.manual.entity;

/**
 * Created by android on 7/27/15.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.movile.up.seriestracker.database.manual.SeriesTrackerDatabase;
import com.movile.up.seriestracker.database.manual.helper.DatabaseHelper;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(databaseName = SeriesTrackerDatabase.NAME)
public class FavoriteEntity extends BaseModel {
    @Column(name = BaseColumns._ID)
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    String slug;

    @Column
    String title;

    public static class FavoriteEntityFields implements BaseColumns {
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_SLUG = "slug";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_SLUG_TYPE = "text";
        public static final String COLUMN_NAME_TYPE = "text";

        public static String createSql() {
            StringBuilder builder = new StringBuilder();
            builder.append("create table ").append(TABLE_NAME).append(" (");
            builder.append(DatabaseHelper.createColumnSql(_ID, "integer", "primary key", true));
            builder.append(DatabaseHelper.createColumnSql(COLUMN_SLUG, COLUMN_SLUG_TYPE, "", true));
            builder.append(DatabaseHelper.createColumnSql(COLUMN_TITLE, COLUMN_NAME_TYPE, "", false));
            builder.append(");");
            return builder.toString();
        }

        public static String dropSql() {
            return DatabaseHelper.dropSql(TABLE_NAME);
        }
    }

    //private String mSlug;
    //private String mTitle;

    public FavoriteEntity() {
    }

    public FavoriteEntity(String mslug, String mtitle) {
        slug = mslug;
        title = mtitle;
    }

    public String slug() {
        return slug;
    }

    public String title() {
        return title;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(FavoriteEntityFields.COLUMN_SLUG, slug);
        values.put(FavoriteEntityFields.COLUMN_TITLE, title);
        return values;
    }

    public FavoriteEntity fromCursor(Cursor cursor) {
        slug = cursor.getString(cursor.getColumnIndexOrThrow(FavoriteEntityFields.COLUMN_TITLE));
        title = cursor.getString(cursor.getColumnIndexOrThrow(FavoriteEntityFields.COLUMN_SLUG));
        return this;
    }
}