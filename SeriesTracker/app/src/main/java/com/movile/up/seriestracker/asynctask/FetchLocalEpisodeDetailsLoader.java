package com.movile.up.seriestracker.asynctask;

/**
 * Created by android on 7/16/15.
 */
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.model.ModelConverter.ModelConverter;
import com.movile.up.seriestracker.model.Episode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class FetchLocalEpisodeDetailsLoader extends AsyncTaskLoader<Episode>{

    private static final String TAG = FetchLocalEpisodeDetailsLoader.class.getSimpleName();
    private Context mContext;
    private String mUrl;

    public FetchLocalEpisodeDetailsLoader(Context context, String url) {
        super(context);
        mContext = context;
        mUrl = url;
    }

    @Override
    public Episode loadInBackground() {
        Episode episode = null;
        InputStreamReader reader = null;

        try {
            HttpURLConnection connection = configureConnection(mContext, mUrl);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error loading remote content", e);
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

    private HttpURLConnection configureConnection(Context context, String url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("trakt-api-version", "2");
        connection.setRequestProperty("trakt-api-key", "718a93a380c6e68c2f782169d31d96fa1c870347643b0355a6c413bfb64338e0");

        return connection;
    }

}