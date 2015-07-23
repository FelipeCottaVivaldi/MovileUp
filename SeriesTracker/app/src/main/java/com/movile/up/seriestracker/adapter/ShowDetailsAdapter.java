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
    public String mShow;

    private Context mContext;

    public ShowDetailsAdapter(FragmentManager manager, Context context, String show) {
        super(manager);
        mContext = context;
        mShow = show;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == POSITION_FIRST_CONTENT) {
            return new ShowDetailsInfoFragment();
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
