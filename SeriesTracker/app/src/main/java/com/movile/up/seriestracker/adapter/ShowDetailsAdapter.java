package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.fragments.ShowDetailsInfoFragment;
import com.movile.up.seriestracker.fragments.ShowDetailsSeasonFragment;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsAdapter extends FragmentPagerAdapter{

    public static final int POSITION_FIRST_CONTENT = 0;
    public static final int POSITION_SECOND_CONTENT = 1;
    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_OVERVIEW = "overview";
    public static final String EXTRA_STATUS = "status";
    public static final String EXTRA_FIRSTAIRED = "firstaired";
    public static final String EXTRA_COUNTRY = "country";
    public static final String EXTRA_LANGUAGE = "language";
    public String mShow;
    private String mOverview;
    private String mStatus;
    private String mFirstAired;
    private String mCountry;
    private String mLanguage;

    private Context mContext;

    public ShowDetailsAdapter(FragmentManager manager, Context context, String show, String overview, String status, String firstaired, String country, String language ) {
        super(manager);
        mContext = context;
        mShow = show;
        mOverview = overview;
        mStatus = status;
        mFirstAired = firstaired;
        mCountry = country;
        mLanguage = language;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == POSITION_FIRST_CONTENT) {
            Bundle b = new Bundle();
            b.putString(EXTRA_OVERVIEW, mOverview);
            b.putString(EXTRA_STATUS, mStatus);
            b.putString(EXTRA_FIRSTAIRED, mFirstAired);
            b.putString(EXTRA_COUNTRY, mCountry);
            b.putString(EXTRA_LANGUAGE, mLanguage);
            ShowDetailsInfoFragment infoFragment = new ShowDetailsInfoFragment();
            infoFragment.setArguments(b);
            return infoFragment;
        }
        if (position == POSITION_SECOND_CONTENT) {
            Bundle b = new Bundle();
            b.putString(EXTRA_SHOW, mShow);
            ShowDetailsSeasonFragment fragment = new ShowDetailsSeasonFragment();
            fragment.setArguments(b);
            return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == POSITION_FIRST_CONTENT) {
            return mContext.getString(R.string.navigation_first_content_label);
        }
        if (position == POSITION_SECOND_CONTENT) {
            return mContext.getString(R.string.navigation_second_content_label);
        }
        return null;
    }
}
