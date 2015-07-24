package com.movile.up.seriestracker.service;

import android.app.IntentService;
import android.content.Intent;

import com.movile.up.seriestracker.model.ShowUpdate;
import com.movile.up.seriestracker.receiver.ShowUpdateReceiver;
import com.movile.up.seriestracker.retrofit.FetchLocalUpdateShowRetrofit;

import retrofit.RestAdapter;

/**
 * Created by android on 7/23/15.
 */
public class UpdatesService extends IntentService {


    public UpdatesService() {
        super(UpdatesService.class.getSimpleName());
    }

    protected void onHandleIntent(Intent intent) {
        Intent intentBroadcast = new Intent("com.movile.up.seriestracker.action.SHOW_UPDATE");
        ShowUpdate update = new FetchLocalUpdateShowRetrofit(this).loadUpdates();
        intentBroadcast.putExtra(ShowUpdateReceiver.EXTRA_UPDATE, update);
        sendBroadcast(intentBroadcast);
    }

}