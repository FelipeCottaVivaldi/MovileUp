package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.ShowDetailsAdapter;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsActivity extends BaseNavigationToolbarActivity{

    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        configureToolbar();
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(new ShowDetailsAdapter(getSupportFragmentManager(), this));
    }

}
