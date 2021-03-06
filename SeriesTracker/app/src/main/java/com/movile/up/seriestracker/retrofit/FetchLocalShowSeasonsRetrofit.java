package com.movile.up.seriestracker.retrofit;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.ShowSeasonsCallback;
import com.movile.up.seriestracker.interfaces.ShowSeasonsRemoteService;
import com.movile.up.seriestracker.model.Season;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/22/15.
 */
public class FetchLocalShowSeasonsRetrofit {

    private static final String TAG = FetchLocalShowSeasonsRetrofit.class.getSimpleName();
    private ShowSeasonsCallback<Season> mListener;
    RestAdapter mAdapter;

    public FetchLocalShowSeasonsRetrofit(Context context, ShowSeasonsCallback<Season> listener){
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.api_url_base)).build();
        mListener = listener;
    }

    public void loadSeasons(String show){
        ShowSeasonsRemoteService service = mAdapter.create(ShowSeasonsRemoteService.class);
        service.getSeasons(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                mListener.OnEpisodesSuccess(seasons);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }
}
