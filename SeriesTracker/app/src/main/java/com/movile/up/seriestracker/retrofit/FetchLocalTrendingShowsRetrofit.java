package com.movile.up.seriestracker.retrofit;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.ShowRemoteService;
import com.movile.up.seriestracker.interfaces.TrendingShowsCallback;
import com.movile.up.seriestracker.model.Show;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/30/15.
 */
public class FetchLocalTrendingShowsRetrofit {

    private static final String TAG = FetchLocalTrendingShowsRetrofit.class.getSimpleName();
    private TrendingShowsCallback<Show> mListener;
    RestAdapter mAdapter;

    public FetchLocalTrendingShowsRetrofit(Context context, TrendingShowsCallback<Show> listener){
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.api_url_base)).build();
        mListener = listener;
    }

    public void loadMovies(){
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);
        service.getMovies(new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                mListener.OnShowsSuccess(shows);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }
}
