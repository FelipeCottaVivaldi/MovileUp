package com.movile.up.seriestracker.retrofit;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.EpisodeDetailsCallback;
import com.movile.up.seriestracker.interfaces.EpisodeRemoteService;
import com.movile.up.seriestracker.interfaces.EpisodesCallBack;
import com.movile.up.seriestracker.model.Episode;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/20/15.
 */
public class FetchLocalEpisodesRetrofit {

    private static final String TAG = FetchLocalEpisodesRetrofit.class.getSimpleName();
    private EpisodesCallBack<Episode> mListener;
    RestAdapter mAdapter;

    public FetchLocalEpisodesRetrofit(Context context, EpisodesCallBack<Episode> listener){
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.api_url_base)).build();
        mListener = listener;
    }

    public void loadEpisodes(String show, Long season){
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodes(show, season, new Callback<List<Episode>>() {
            @Override
            public void success(List<Episode> episode, Response response) {
                mListener.OnEpisodesSuccess(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }
}
