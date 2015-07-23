package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.interfaces.PopularShowsCallback;
import com.movile.up.seriestracker.interfaces.PopularShowsView;
import com.movile.up.seriestracker.interfaces.ShowSeasonsView;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.retrofit.FetchLocalPopularShowsRetrofit;
import com.movile.up.seriestracker.retrofit.FetchLocalShowSeasonsRetrofit;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class PopularShowsPresenter implements PopularShowsCallback<Show> {

    PopularShowsView mView;
    Context mContext;

    public PopularShowsPresenter(Context context, PopularShowsView view){
        mView = view;
        mContext = context;
    }

    public void loadShows(){
        new FetchLocalPopularShowsRetrofit(mContext, this).loadShows();
    }
    @Override
    public void OnShowsSuccess(List<Show> x) {
        mView.displayShows(x);
    }
}
