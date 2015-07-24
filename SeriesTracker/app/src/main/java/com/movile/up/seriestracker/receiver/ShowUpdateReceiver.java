package com.movile.up.seriestracker.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.model.ShowUpdate;
import com.movile.up.seriestracker.util.FormatUtil;

/**
 * Created by android on 7/23/15.
 */
public class ShowUpdateReceiver extends BroadcastReceiver {

    private static final String TAG = ShowUpdateReceiver.class.getSimpleName();
    public static final String EXTRA_UPDATE = "update";
    private ShowUpdate update;
    public String lastDate;
    public static final String PREFERENCES_NAME = "preferences";
    public static final String KEY_LAST_UPDATE = "lastUpdate";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        update = (ShowUpdate) extras.get(EXTRA_UPDATE);


        Intent i = new Intent(context, ShowDetailsActivity.class);
        i.putExtra(ShowDetailsActivity.EXTRA_SHOW, update.show());
        i.putExtra(ShowDetailsActivity.EXTRA_TITLE, update.title());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(ShowDetailsActivity.class);
        stackBuilder.addNextIntent(i);

        PendingIntent action = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // GET SHARED PREFERENCES
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        // GET VALUE
        if ((preferences.getString(KEY_LAST_UPDATE, null) == null) ||
                (preferences.getString(KEY_LAST_UPDATE, null).compareTo(FormatUtil.formatDate(FormatUtil.formatDate(update.date())))) < 1){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY_LAST_UPDATE, FormatUtil.formatDate(FormatUtil.formatDate(update.date())));
            editor.commit();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle(update.title())
                    .setContentText(update.message())
                    .setContentIntent(action)
                    .setAutoCancel(true)
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(update.message()));

            Notification notification = builder.build();
            NotificationManager manager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            manager.notify(0, notification);}

    }
}
