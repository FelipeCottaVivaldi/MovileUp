package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.PopularShowsFragment;
import com.movile.up.seriestracker.activity.PopularMoviesFragment;

/**
 * Created by android on 7/30/15.
 */

    /**
     * Created by android on 7/21/15.
     */
    public class GridShowAdapter extends FragmentPagerAdapter {

        public static final int POSITION_FIRST_CONTENT = 0;
        public static final int POSITION_SECOND_CONTENT = 1;

        private Context mContext;

        public GridShowAdapter(FragmentManager fm, Context context) {
            super(fm);
            mContext = context;
        }


        @Override
        public Fragment getItem(int position) {
            if (position == POSITION_FIRST_CONTENT) {
                return new PopularShowsFragment();
            }
            if (position == POSITION_SECOND_CONTENT) {
                return new PopularMoviesFragment();
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
                return mContext.getString(R.string.popular_shows);
            }
            if (position == POSITION_SECOND_CONTENT) {
                return mContext.getString(R.string.movies);
            }
            return null;
        }
    }
