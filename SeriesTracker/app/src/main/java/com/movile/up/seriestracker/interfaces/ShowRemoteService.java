package com.movile.up.seriestracker.interfaces;

import android.support.v7.util.SortedList;

import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.ApiConfiguration;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;

/**
 * Created by android on 7/23/15.
 */
public interface ShowRemoteService {

    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/popular?limit=100&extended=full,images")
    void getShows(
            Callback<List<Show>> callback);
}
