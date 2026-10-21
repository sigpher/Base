package com.sigpher.demobase;

import android.app.Application;
import com.squareup.leakcanary.LeakCanary;

/**
 * @author Aaron aaronzheng9603@gmail.com
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
