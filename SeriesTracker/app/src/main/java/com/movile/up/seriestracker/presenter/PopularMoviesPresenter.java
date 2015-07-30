package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.PopularShowsView;
import com.movile.up.seriestracker.interfaces.TrendingShowsCallback;
import com.movile.up.seriestracker.interfaces.TrendingShowsView;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.retrofit.FetchLocalPopularShowsRetrofit;
import com.movile.up.seriestracker.retrofit.FetchLocalTrendingShowsRetrofit;

import java.util.List;

/**
 * Created by android on 7/30/15.
 */
public class PopularMoviesPresenter implements TrendingShowsCallback<Show> {
    TrendingShowsView mView;
    Context mContext;

    public PopularMoviesPresenter(Context context, TrendingShowsView view){
        mView = view;
        mContext = context;
    }

    public void loadMovies(){
        new FetchLocalTrendingShowsRetrofit(mContext, this).loadMovies();
    }
    @Override
    public void OnShowsSuccess(List<Show> x) {
        mView.displayShows(x);
    }
}
