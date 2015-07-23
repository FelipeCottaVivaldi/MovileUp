package com.movile.up.seriestracker.activity;

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

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class PopularShowsActivity extends AppCompatActivity implements PopularShowsView, OnShowListener {

    private PopularShowsAdapter mAdapter;
    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_TITLE = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popular_shows_activitiy);
        new PopularShowsPresenter(this, this).loadShows();

        GridView gridview = (GridView) findViewById(R.id.shows_grid_view);
        mAdapter = new PopularShowsAdapter(this, this);
        gridview.setAdapter(mAdapter);


    }

    @Override
    public void onShowClick(Show show) {
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, show.ids().slug());
        intent.putExtra(EXTRA_TITLE, show.title());
        startActivity(intent);
    }

    @Override
    public void displayShows(List<Show> shows) {
        mAdapter.updateShows(shows);

    }
}
