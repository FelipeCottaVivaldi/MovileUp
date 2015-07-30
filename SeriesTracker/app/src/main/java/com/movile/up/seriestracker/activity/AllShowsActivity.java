package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.GridShowAdapter;
import com.movile.up.seriestracker.adapter.ShowDetailsAdapter;

/**
 * Created by android on 7/30/15.
 */
public class AllShowsActivity extends BaseNavigationDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_shows_activity);
        configureNavigation();


        ViewPager pager = (ViewPager) findViewById(R.id.shows_view_pager);
        pager.setAdapter(new GridShowAdapter(this.getSupportFragmentManager(), this));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.shows_tab);
        tabLayout.setupWithViewPager(pager);
    }
}
