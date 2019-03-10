package slabko.vladislav.slabkovladlauncher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;

import com.crashlytics.android.Crashlytics;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

import io.fabric.sdk.android.Fabric;
import slabko.vladislav.slabkovladlauncher.AsyncTasks.AsyncAppsInfo;
import slabko.vladislav.slabkovladlauncher.global.Constants;

public class PreStartActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener  {

    private static boolean loadedFromSharedPreferences = false;
    private int currentTheme = Constants.THEME_DEFAULT_ID;
    private String currentModel = Constants.MODEL_DEFAULT;
    private String currentSort = Constants.SORT_DEFAULT;
    private boolean showWelcomePage = true;
    private final String API_key = "0d309191-768e-409f-b4ad-0e56fd386ed2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(API_key).build();
        YandexMetrica.activate(getApplicationContext(), config);
        YandexMetrica.enableActivityAutoTracking(getApplication());

        Fabric.with(this, new Crashlytics());

        final Intent intent = new Intent();
        intent.setClass(getApplicationContext(), MainActivity.class);
        startActivity(intent);

        AsyncAppsInfo appTask = new AsyncAppsInfo();
        appTask.execute(getApplicationContext());
    }
    @Override
    protected void onResume() {
        super.onResume();
        PreferenceManager
                .getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        PreferenceManager
                .getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (sharedPreferences == null)
            return;
        switch (key) {
            case Constants.KEY_THEME:
                changeTheme(sharedPreferences, key);
                break;
            case Constants.KEY_MODEL:
                //changeModel(sharedPreferences, key);
                break;
            case Constants.KEY_SORT:
                //changeSort(sharedPreferences, key);
                break;
            case Constants.KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD:
                //changeShowWelcomePage(sharedPreferences, key);
                break;
        }
    }

    private void changeTheme(SharedPreferences sharedPreferences, String key) {
        currentTheme = loadThemeFromSharedPreferences(sharedPreferences, key);
        recreate();
    }

    @Override
    public void setTheme(int resid) {
        if (!loadedFromSharedPreferences) {
            loadSettingsFromSharedPreferences();
            loadedFromSharedPreferences = true;
        }
        super.setTheme(currentTheme);
    }

    private int getTheme(String themeType) {
        switch(themeType) {
            case Constants.THEME_LIGHT:
                return Constants.THEME_LIGHT_ID;
            case Constants.THEME_DARK:
                return Constants.THEME_DARK_ID;

                default: throw new IllegalArgumentException("$childClassName.getTheme(). Unknown theme type");
        }
    }

    private int loadThemeFromSharedPreferences(SharedPreferences sharedPreferences, String key) {
        String newThemeType = sharedPreferences.getString(key, Constants.THEME_LIGHT);
        return getTheme(newThemeType);
    }

    private String loadModelFromSharedPreferences(SharedPreferences sharedPreferences, String key) {
        return sharedPreferences.getString(key, Constants.MODEL_DEFAULT);
    }

    private String loadSortFromSharedPreferences(SharedPreferences sharedPreferences, String key) {
            return sharedPreferences.getString(key, Constants.SORT_DEFAULT);
    }

    private boolean loadShowWelcomePageFromSharedPreferences(SharedPreferences sharedPreferences, String key) {
        return sharedPreferences.getBoolean(key, true);
    }

    private void loadSettingsFromSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentTheme = loadThemeFromSharedPreferences(sharedPreferences, Constants.KEY_THEME);
        currentModel = loadModelFromSharedPreferences(sharedPreferences, Constants.KEY_MODEL);
        currentSort = loadSortFromSharedPreferences(sharedPreferences, Constants.KEY_SORT);
        showWelcomePage = loadShowWelcomePageFromSharedPreferences(
                sharedPreferences,
                Constants.KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD
        );
    }
}
