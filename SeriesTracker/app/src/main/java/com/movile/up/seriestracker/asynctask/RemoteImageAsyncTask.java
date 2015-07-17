package com.movile.up.seriestracker.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;

/**
 * Created by android on 7/17/15.
 */
public class RemoteImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private static final String TAG = RemoteImageAsyncTask.class.getSimpleName();

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
        } catch (IOException e) {
            Log.e(TAG, "Error fetching image from " + url, e);
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
    }
}
