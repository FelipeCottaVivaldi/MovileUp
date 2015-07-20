package com.movile.up.seriestracker.activity;

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
public class SeasonDetailsActivity extends AppCompatActivity implements SeasonDetailsView {

    private List<Episode> mEpisodes;
    private OnContentClickListener<Episode> clickListener;
    public EpisodesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);
        new EpisodesPresenter(this, this).loadEpisodes();
        ListView view = (ListView) findViewById(R.id.season_details_list_view);

        adapter = new EpisodesAdapter(this, clickListener);
        view.setAdapter(adapter);


    }

    @Override
    public void displayEpisodes(List<Episode> episode) {
        adapter.updateEpisodes(episode);
    }
}
