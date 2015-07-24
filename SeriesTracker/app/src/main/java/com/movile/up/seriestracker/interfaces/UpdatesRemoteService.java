package com.movile.up.seriestracker.interfaces;

/**
 * Created by android on 7/23/15.
 */
import com.movile.up.seriestracker.model.ShowUpdate;

import retrofit.http.GET;

public interface UpdatesRemoteService {

    @GET("/latestUpdate.json")
    ShowUpdate getLatest();

}