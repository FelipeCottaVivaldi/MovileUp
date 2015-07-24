package com.movile.up.seriestracker.retrofit;

import android.content.Context;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.UpdatesRemoteService;
import com.movile.up.seriestracker.model.ShowUpdate;

import retrofit.RestAdapter;

/**
 * Created by android on 7/24/15.
 */
public class FetchLocalUpdateShowRetrofit {
    RestAdapter mAdapter;

    public FetchLocalUpdateShowRetrofit(Context context){
        mAdapter = new RestAdapter.Builder().setEndpoint(context.getResources().getString(R.string.api_url_updates)).build();
    }

    public ShowUpdate loadUpdates(){
        UpdatesRemoteService service = mAdapter.create(UpdatesRemoteService.class);
        return service.getLatest();
    }
}
