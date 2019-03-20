package slabko.vladislav.slabkovladlauncher;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.widget.BaseAdapter;

import com.crashlytics.android.Crashlytics;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

import io.fabric.sdk.android.Fabric;
import slabko.vladislav.slabkovladlauncher.AsyncTasks.AsyncAppsInfo;
import slabko.vladislav.slabkovladlauncher.global.Constants;

public class AppLauncher extends PreStartActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onResume();


        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(Constants.API_KEY).build();
        YandexMetrica.activate(getApplicationContext(), config);
        YandexMetrica.enableActivityAutoTracking(getApplication());

        Fabric.with(this, new Crashlytics());

        final Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        AsyncAppsInfo appTask = new AsyncAppsInfo();
        appTask.execute(getApplicationContext());
    }


}
