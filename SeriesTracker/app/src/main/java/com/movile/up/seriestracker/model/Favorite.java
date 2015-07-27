package com.movile.up.seriestracker.model;

/**
 * Created by android on 7/27/15.
 */
public class Favorite {

    private String mSlug;
    private String mTitle;

    public Favorite(String slug, String title) {
        mSlug = slug;
        mTitle = title;
    }

    public String slug() {
        return mSlug;
    }

    public String title() {
        return mTitle;
    }

}