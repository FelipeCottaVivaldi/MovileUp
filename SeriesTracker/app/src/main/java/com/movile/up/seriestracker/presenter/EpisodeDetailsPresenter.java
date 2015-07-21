package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.EpisodeDetailsCallback;
import com.movile.up.seriestracker.interfaces.EpisodeDetailsView;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.retrofit.FetchLocalEpisodeDetailsRetrofit;

/**
 * Created by android on 7/17/15.
 */
public class EpisodeDetailsPresenter implements EpisodeDetailsCallback<Episode>{

    EpisodeDetailsView mView;
    Context mContext;

    public EpisodeDetailsPresenter(Context context, EpisodeDetailsView view){
        mView = view;
        mContext = context;
    }

    public void loadEpisode(String show, Long season, Long episode){
        new FetchLocalEpisodeDetailsRetrofit(mContext, this).loadEpisode(show, season, episode);
    }

    @Override
    public void OnEpisodeDetailsSucess(Episode episode) {
        mView.displayEpisode(episode);
    }
}
