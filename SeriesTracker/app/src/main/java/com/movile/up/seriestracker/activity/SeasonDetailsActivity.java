package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.EpisodesAdapter;
import com.movile.up.seriestracker.interfaces.OnContentClickListener;
import com.movile.up.seriestracker.interfaces.SeasonDetailsView;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.presenter.EpisodesPresenter;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements SeasonDetailsView, OnContentClickListener {

    private List<Episode> mEpisodes;
    private OnContentClickListener clickListener;
    public EpisodesAdapter adapter;

    private String mShow;
    private Long mSeason;
    private Double mRating;
    private String mScreenshot;
    private String mPoster;

    public static final String EXTRA_SHOW = "show";
    public static final String EXTRA_SEASON = "season";
    public static final String EXTRA_EPISODE = "episode";
    public static final String EXTRA_RATING = "rating";
    public static final String EXTRA_SCREENSHOT = "screenshot";
    public static final String EXTRA_POSTER = "poster";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        configureToolbar();
        showLoading();
        extractInformationsFromExtra();
        new EpisodesPresenter(this, this).loadEpisodes(mShow, mSeason);
        ListView view = (ListView) findViewById(R.id.season_details_list_view);

        View headerView = getLayoutInflater().inflate(R.layout.season_details_header, null, false);

        view.addHeaderView(headerView);

        adapter = new EpisodesAdapter(this, this);
        view.setAdapter(adapter);


    }

    private void extractInformationsFromExtra() {
        Bundle extras = getIntent().getExtras();
        mShow = extras.getString(EXTRA_SHOW);
        mSeason = extras.getLong(EXTRA_SEASON);
        mRating = extras.getDouble(EXTRA_RATING);
        mScreenshot = extras.getString(EXTRA_SCREENSHOT);
        mPoster = extras.getString(EXTRA_POSTER);
    }

    public void onEpisodeClick(Episode episode){
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(EXTRA_SHOW, mShow);
        intent.putExtra(EXTRA_SEASON, mSeason);
        intent.putExtra(EXTRA_EPISODE, episode.number());
        startActivity(intent);
    }

    @Override
    public void displayEpisodes(List<Episode> episodes) {
        adapter.updateEpisodes(episodes);
        ((TextView) findViewById(R.id.season_details_rating)).setText(mRating.toString());
        Glide
                .with(this)
                .load(mScreenshot)
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.season_details_screenshot));
        Glide
                .with(this)
                .load(mPoster)
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.season_details_thumb_show));
        getSupportActionBar().setTitle("Season " + episodes.get(0).season().toString());
        hideLoading();
    }
}
