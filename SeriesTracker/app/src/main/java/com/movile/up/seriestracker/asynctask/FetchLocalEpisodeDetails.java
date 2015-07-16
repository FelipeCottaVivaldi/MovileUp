package com.movile.up.seriestracker.asynctask;

/**
 * Created by android on 7/16/15.
 */
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.movile.up.seriestracker.EpisodeDetailsActivity;
import com.movile.up.seriestracker.model.ModelConverter.ModelConverter;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.interfaces.OnOperationListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FetchLocalEpisodeDetails extends AsyncTask<Void, Void, Episode> {

    private static final String TAG = FetchLocalEpisodeDetails.class.getSimpleName();
    private static final String ASSET_NAME = "episode.json";
    private static Context context;
    private OnOperationListener mListener;

    public FetchLocalEpisodeDetails(Context contexts, OnOperationListener listener){
        this.context = contexts;
        this.mListener = listener;
    }

    @Override
    protected Episode doInBackground(Void... params){
        Episode episode = null;
        InputStreamReader reader = null;

        try {
            InputStream stream = this.context.getResources().getAssets().open(ASSET_NAME);
            reader = new InputStreamReader(stream);
            episode = new ModelConverter().toEpisode(reader);
        } catch (IOException e) {
            Log.e(TAG, "Error loading local content from file", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error releasing resource", e);
                }
            }
        }

        return episode;
    }

    @Override
    protected void onPostExecute(Episode episode) {
        super.onPostExecute(episode);
        mListener.onOperationSucess(episode);
    }


}