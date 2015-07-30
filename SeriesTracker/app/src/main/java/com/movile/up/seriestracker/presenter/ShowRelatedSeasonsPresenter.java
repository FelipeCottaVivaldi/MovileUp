package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.PopularShowsCallback;
import com.movile.up.seriestracker.interfaces.PopularShowsView;
import com.movile.up.seriestracker.interfaces.ShowSeasonsCallback;
import com.movile.up.seriestracker.interfaces.ShowSeasonsView;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.retrofit.FetchLocalShowRelatedSeasonsRetrofit;
import com.movile.up.seriestracker.retrofit.FetchLocalShowSeasonsRetrofit;

import java.util.List;

/**
 * Created by android on 7/30/15.
 */
public class ShowRelatedSeasonsPresenter implements PopularShowsCallback<Show> {
    PopularShowsView mView;
    Context mContext;

    public ShowRelatedSeasonsPresenter(Context context, PopularShowsView view){
        mView = view;
        mContext = context;
    }

    public void loadRelatedSeasons(String show){
        new FetchLocalShowRelatedSeasonsRetrofit(mContext, this).loadRelatedSeasons(show);
    }

    @Override
    public void OnShowsSuccess(List<Show> x) {
        mView.displayShows(x);
    }
}
