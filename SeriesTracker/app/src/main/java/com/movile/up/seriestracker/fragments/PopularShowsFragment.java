package com.movile.up.seriestracker.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.adapter.PopularShowsAdapter;
import com.movile.up.seriestracker.interfaces.OnShowListener;
import com.movile.up.seriestracker.interfaces.PopularShowsView;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.PopularShowsPresenter;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class PopularShowsFragment extends Fragment implements PopularShowsView, OnShowListener {

    private View mView;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.popular_shows_activitiy, container, false);
        return mView;
    }


    @Override
    public void onStart() {
        super.onStart();
        new PopularShowsPresenter(this.getActivity(), this).loadShows();

        GridView gridview = (GridView) this.getActivity().findViewById(R.id.shows_grid_view);
        mAdapter = new PopularShowsAdapter(this.getActivity(), this);
        gridview.setAdapter(mAdapter);

    }

    @Override
    public void onShowClick(Show show) {
        Intent intent = new Intent(this.getActivity(), ShowDetailsActivity.class);
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
