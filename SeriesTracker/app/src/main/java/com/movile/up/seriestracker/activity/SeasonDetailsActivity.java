package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.EpisodesAdapter;
import com.movile.up.seriestracker.interfaces.OnContentClickListener;
import com.movile.up.seriestracker.interfaces.SeasonDetailsView;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.presenter.EpisodesPresenter;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements SeasonDetailsView, OnContentClickListener {

    private List<Episode> mEpisodes;
    private OnContentClickListener clickListener;
    public EpisodesAdapter adapter;

    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_SEASON = "season";
    public static final String EXTRA_EPISODE = "episode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        configureToolbar();
        showLoading();
        new EpisodesPresenter(this, this).loadEpisodes("prison-break", 4l);
        ListView view = (ListView) findViewById(R.id.season_details_list_view);

        adapter = new EpisodesAdapter(this, this);
        view.setAdapter(adapter);


    }

    public void onEpisodeClick(Episode episode){
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, "prison-break");
        intent.putExtra(EXTRA_SEASON, 4l);
        intent.putExtra(EXTRA_EPISODE, episode.number());
        startActivity(intent);
    }

    @Override
    public void displayEpisodes(List<Episode> episodes) {
        adapter.updateEpisodes(episodes);
        getSupportActionBar().setTitle("Season " + episodes.get(0).season().toString());
        hideLoading();
    }
}
