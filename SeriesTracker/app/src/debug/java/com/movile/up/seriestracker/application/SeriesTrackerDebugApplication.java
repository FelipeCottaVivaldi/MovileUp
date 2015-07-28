package com.movile.up.seriestracker.application;

/**
 * Created by android on 7/24/15.
 */
import android.app.Application;

import com.facebook.stetho.Stetho;
import com.raizlabs.android.dbflow.config.FlowManager;

public class SeriesTrackerDebugApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(this);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

}