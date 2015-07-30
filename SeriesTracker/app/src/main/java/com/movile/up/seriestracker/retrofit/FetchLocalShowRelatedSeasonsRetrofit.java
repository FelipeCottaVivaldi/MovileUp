package com.movile.up.seriestracker.retrofit;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.PopularShowsCallback;
import com.movile.up.seriestracker.interfaces.ShowSeasonsCallback;
import com.movile.up.seriestracker.interfaces.ShowSeasonsRemoteService;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.model.Show;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/30/15.
 */
public class FetchLocalShowRelatedSeasonsRetrofit {

    private static final String TAG = FetchLocalShowRelatedSeasonsRetrofit.class.getSimpleName();
    private PopularShowsCallback<Show> mListener;
    RestAdapter mAdapter;

    public FetchLocalShowRelatedSeasonsRetrofit(Context context, PopularShowsCallback<Show> listener){
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.api_url_base)).build();
        mListener = listener;
    }

    public void loadRelatedSeasons(String show){
        ShowSeasonsRemoteService service = mAdapter.create(ShowSeasonsRemoteService.class);
        service.getRelatedSeasons(show, new Callback<List<Show>>() {
            @Override
            public void success(List<Show> seasons, Response response) {
                mListener.OnShowsSuccess(seasons);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }
}
