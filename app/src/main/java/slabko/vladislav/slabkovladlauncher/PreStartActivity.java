package slabko.vladislav.slabkovladlauncher;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.preference.PreferenceManager;

import com.crashlytics.android.Crashlytics;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

import io.fabric.sdk.android.Fabric;
import slabko.vladislav.slabkovladlauncher.AsyncTasks.AsyncAppsInfo;
import slabko.vladislav.slabkovladlauncher.additional.AppInfo;
import slabko.vladislav.slabkovladlauncher.containers.GridFragment;
import slabko.vladislav.slabkovladlauncher.global.Constants;

public class PreStartActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener  {

    private static boolean loadedFromSharedPreferences = false;
    private int currentTheme = Constants.THEME_DEFAULT_ID;
    private String currentModel = Constants.MODEL_DEFAULT;
    private String currentSort = Constants.SORT_DEFAULT;
    private boolean showWelcomePage = true;


    @Override
    protected void onResume() {
        Constants.appContext = this;
        super.onResume();
        loadSettingsFromSharedPreferences();
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
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }


    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
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
                changeModel(sharedPreferences, key);
                break;
            case Constants.SETTINGS_MODEL:
                String curModel = sharedPreferences.getString(key, Constants.MODEL_DEFAULT);
                //MainActivity.mRecreate();
                Constants.MODEL_CURRENT = curModel;
                sharedPreferences
                        .edit()
                        .putString(Constants.KEY_MODEL, curModel)
                        .apply();
                GridFragment.changeGridLayout(curModel);
                break;
            case Constants.SETTINGS_THEME:
                //MainActivity.mRecreate();
                break;
            case Constants.SETTINGS_SORT:
                changeSort(sharedPreferences, key);
                break;
            case Constants.KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD:
                //changeShowWelcomePage(sharedPreferences, key);
                break;
                default:
                    //onSortChancged(sharedPreferences, key);
                    break;
        }
    }


    private void changeTheme(SharedPreferences sharedPreferences, String key) {
        currentTheme = loadThemeFromSharedPreferences(sharedPreferences, key);
        setTheme(currentTheme);
        recreate();
    }

    private void changeSort(SharedPreferences sharedPreferences, String key) {
        currentSort = loadSortFromSharedPreferences(sharedPreferences, key);
        Constants.SORT_CURRENT = currentSort;
        AppInfo.sortInfo(true);
    }

    private void changeModel(SharedPreferences sharedPreferences, String key) {
        currentModel = loadModelFromSharedPreferences(sharedPreferences, key);
        //GridFragment.changeGridLayout(currentModel);
    }


    @Override
    public void setTheme(int resid) {
       /* if (!loadedFromSharedPreferences) {
            //loadSettingsFromSharedPreferences();
            loadedFromSharedPreferences = true;
        }*/
        int mode;
        if(currentTheme == Constants.THEME_LIGHT_ID) {
            mode = AppCompatDelegate.MODE_NIGHT_NO;
        } else {
            mode = AppCompatDelegate.MODE_NIGHT_YES;
        }
        AppCompatDelegate.setDefaultNightMode(mode);
        super.setTheme(currentTheme);
    }


    private int getTheme(SharedPreferences sharedPreferences, String themeType) {
        switch(themeType) {
            case Constants.THEME_LIGHT:
                sharedPreferences
                        .edit()
                        .putBoolean("list_theme", false)
                        .apply();
                return Constants.THEME_LIGHT_ID;
            case Constants.THEME_DARK:
                sharedPreferences
                        .edit()
                        .putBoolean("list_theme", true)
                        .apply();
                return Constants.THEME_DARK_ID;

                default: throw new IllegalArgumentException("$childClassName.getTheme(). Unknown theme type");
        }
    }


    private int loadThemeFromSharedPreferences(SharedPreferences sharedPreferences, String key) {
        String newThemeType = sharedPreferences.getString(key, Constants.THEME_LIGHT);
        return getTheme(sharedPreferences, newThemeType);
    }


    private String loadModelFromSharedPreferences(SharedPreferences sharedPreferences, String key) {
        String newModelType = sharedPreferences.getString(key, Constants.MODEL_DEFAULT);
        sharedPreferences
                .edit()
                .putString("list_maquette", newModelType)
                .apply();
        Constants.MODEL_CURRENT = newModelType;
        return newModelType;
    }


    private String loadSortFromSharedPreferences(SharedPreferences sharedPreferences, String key) {
        String newSortType = sharedPreferences.getString(key, Constants.SORT_DEFAULT);
        /*sharedPreferences
                .edit()
                .putString("sort", newSortType)
                .apply();*/
        return newSortType;
    }


    private boolean loadShowWelcomePageFromSharedPreferences(SharedPreferences sharedPreferences, String key) {
        return sharedPreferences.getBoolean(key, true);
    }


    private void loadSettingsFromSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Constants.THEME_CURRENT_ID = loadThemeFromSharedPreferences(sharedPreferences, Constants.KEY_THEME);
        Constants.MODEL_CURRENT = loadModelFromSharedPreferences(sharedPreferences, Constants.KEY_MODEL);
        Constants.SORT_CURRENT = loadSortFromSharedPreferences(sharedPreferences, Constants.KEY_SORT);
        Constants.SHOW_WELCOME_PAGE = loadShowWelcomePageFromSharedPreferences(
                sharedPreferences,
                Constants.KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD);
    }

}
