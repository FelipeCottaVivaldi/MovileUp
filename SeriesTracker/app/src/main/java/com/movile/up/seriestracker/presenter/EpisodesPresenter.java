package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.EpisodeDetailsView;
import com.movile.up.seriestracker.interfaces.EpisodesCallBack;
import com.movile.up.seriestracker.interfaces.SeasonDetailsView;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.retrofit.FetchLocalEpisodeDetailsRetrofit;
import com.movile.up.seriestracker.retrofit.FetchLocalEpisodesRetrofit;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public class EpisodesPresenter implements EpisodesCallBack<Episode> {

    SeasonDetailsView mView;
    Context mContext;

    public EpisodesPresenter(Context context, SeasonDetailsView view){
        mView = view;
        mContext = context;
    }

    public void loadEpisodes(String show, Long season){
        new FetchLocalEpisodesRetrofit(mContext, this).loadEpisodes(show, season);
    }

    @Override
    public void OnEpisodesSuccess(List<Episode> episode) {
        mView.displayEpisodes(episode);
    }
}
