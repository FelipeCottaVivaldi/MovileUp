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
    private String mShow;
    public static final String EXTRA_SHOW = "show";
    private String mTitle;
    public static final String EXTRA_TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        configureToolbar();
        extractInformationsFromExtra();
        getSupportActionBar().setTitle(mTitle);
        ViewPager pager = (ViewPager) findViewById(R.id.view_pager);
        pager.setAdapter(new ShowDetailsAdapter(getSupportFragmentManager(), this, mShow));
    }

    private void extractInformationsFromExtra() {
        Bundle extras = getIntent().getExtras();
        mShow = extras.getString(EXTRA_SHOW);
        mTitle = extras.getString(EXTRA_TITLE);
    }

}
