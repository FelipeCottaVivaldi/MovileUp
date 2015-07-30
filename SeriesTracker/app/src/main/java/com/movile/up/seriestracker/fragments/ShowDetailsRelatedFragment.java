package com.movile.up.seriestracker.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.SeasonDetailsActivity;
import com.movile.up.seriestracker.adapter.ShowSeasonsRecyclerAdapter;
import com.movile.up.seriestracker.interfaces.OnSeasonsClickListener;
import com.movile.up.seriestracker.interfaces.ShowSeasonsView;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.presenter.ShowRelatedSeasonsPresenter;
import com.movile.up.seriestracker.presenter.ShowSeasonsPresenter;

import java.util.List;

/**
 * Created by android on 7/30/15.
 */
public class ShowDetailsRelatedFragment extends Fragment implements ShowSeasonsView, OnSeasonsClickListener{

    private ShowSeasonsRecyclerAdapter mAdapter;
    private View mView;
    private String mShow;
    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_SEASON = "season";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_SCREENSHOT = "screenshot";
    public static final String EXTRA_POSTER = "poster";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.show_details_related_fragment, container, false);
        return mView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle b = this.getArguments();
        mShow = b.getString(EXTRA_SHOW);
        new ShowRelatedSeasonsPresenter(this.getActivity(), this).loadRelatedSeasons(mShow);
        configureRecyclerView();
    }

    private void configureRecyclerView() {
        RecyclerView view = (RecyclerView) mView.findViewById(R.id.show_related_recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));

        mAdapter = new ShowSeasonsRecyclerAdapter(this.getActivity(), this);
        view.setAdapter(mAdapter);
    }

    @Override
    public void onSeasonClick(Season season) {
        Intent intent = new Intent(this.getActivity(), SeasonDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, mShow);
        intent.putExtra(EXTRA_SEASON, season.number());
        intent.putExtra(EXTRA_RATING, season.rating());
        intent.putExtra(EXTRA_SCREENSHOT, season.images().thumb().get("full"));
        intent.putExtra(EXTRA_POSTER, season.images().poster().get("full"));
        startActivity(intent);
    }

    @Override
    public void displaySeasons(List<Season> seasons) {
        mAdapter.updateSeasons(seasons);
    }
}
