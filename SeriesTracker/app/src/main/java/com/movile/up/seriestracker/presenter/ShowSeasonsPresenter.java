package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.SeasonDetailsView;
import com.movile.up.seriestracker.interfaces.ShowSeasonsCallback;
import com.movile.up.seriestracker.interfaces.ShowSeasonsView;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.retrofit.FetchLocalEpisodesRetrofit;
import com.movile.up.seriestracker.retrofit.FetchLocalShowSeasonsRetrofit;

import java.util.List;

/**
 * Created by android on 7/22/15.
 */
public class ShowSeasonsPresenter implements ShowSeasonsCallback<Season> {

    ShowSeasonsView mView;
    Context mContext;

    public ShowSeasonsPresenter(Context context, ShowSeasonsView view){
        mView = view;
        mContext = context;
    }

    public void loadEpisodes(String show){
        new FetchLocalShowSeasonsRetrofit(mContext, this).loadSeasons(show);
    }

    @Override
    public void OnEpisodesSuccess(List<Season> seasons) {
        mView.displaySeasons(seasons);
    }
}
