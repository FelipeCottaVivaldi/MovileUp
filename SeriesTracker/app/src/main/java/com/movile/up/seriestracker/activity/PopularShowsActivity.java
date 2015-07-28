package com.movile.up.seriestracker.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.EpisodesAdapter;
import com.movile.up.seriestracker.adapter.PopularShowsAdapter;
import com.movile.up.seriestracker.interfaces.OnShowListener;
import com.movile.up.seriestracker.interfaces.PopularShowsView;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.EpisodesPresenter;
import com.movile.up.seriestracker.presenter.PopularShowsPresenter;
import com.movile.up.seriestracker.service.UpdatesService;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class PopularShowsActivity extends BaseNavigationDrawerActivity implements PopularShowsView, OnShowListener {

    private PopularShowsAdapter mAdapter;
    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_SCREENSHOT = "screenshot";
    public static final String EXTRA_OVERVIEW = "overview";
    public static final String EXTRA_STATUS = "status";
    public static final String EXTRA_FIRSTAIRED = "firstaired";
    public static final String EXTRA_COUNTRY = "country";
    public static final String EXTRA_LANGUAGE = "language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_shows_activitiy);
        configureNavigation();
        new PopularShowsPresenter(this, this).loadShows();

        GridView gridview = (GridView) findViewById(R.id.shows_grid_view);
        mAdapter = new PopularShowsAdapter(this, this);
        gridview.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onShowClick(Show show) {
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, show.ids().slug());
        intent.putExtra(EXTRA_TITLE, show.title());
        intent.putExtra(EXTRA_RATING, show.rating());
        intent.putExtra(EXTRA_SCREENSHOT, show.images().thumb().get("full"));
        intent.putExtra(EXTRA_OVERVIEW, show.overview());
        intent.putExtra(EXTRA_STATUS, show.status());
        intent.putExtra(EXTRA_FIRSTAIRED, show.firstAired());
        intent.putExtra(EXTRA_COUNTRY, show.country());
        intent.putExtra(EXTRA_LANGUAGE, show.language());
        startActivity(intent);
    }

    @Override
    public void displayShows(List<Show> shows) {
        mAdapter.updateShows(shows);

    }
}
