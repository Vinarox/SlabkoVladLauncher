package slabko.vladislav.slabkovladlauncher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.Display;
import android.view.WindowManager;

import com.crashlytics.android.Crashlytics;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.YandexMetricaConfig;

import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ExecutionException;

import io.fabric.sdk.android.Fabric;
import slabko.vladislav.slabkovladlauncher.AsyncTasks.AsyncAppsInfo;
import slabko.vladislav.slabkovladlauncher.global.Constants;
import slabko.vladislav.slabkovladlauncher.welcomePage.FirstFragment;
import slabko.vladislav.slabkovladlauncher.welcomePage.FourthFragment;
import slabko.vladislav.slabkovladlauncher.welcomePage.SecondFragment;
import slabko.vladislav.slabkovladlauncher.welcomePage.ThirdFragment;

public class MainActivity extends PreStartActivity{

   static MainActivity mainActivity;
    ViewPager pager;
    PagerAdapter pagerAdapter;
    String[] titles = {"App Name", "App Description", "Background", "Scheme"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = this;

        WindowManager wm = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        new Constants(size.x, size.y);

        //AsyncAppsInfo appTask = new AsyncAppsInfo();
        //appTask.execute(getApplicationContext());


        //YandexMetricaConfig config = YandexMetricaConfig.newConfigBuilder(API_key).build();
        //YandexMetrica.activate(getApplicationContext(), config);
        //YandexMetrica.enableActivityAutoTracking(getApplication());

        //Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    public void selectPage(int page) {
        pager.setCurrentItem(page);
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return FirstFragment.newInstance(position);
                case 1:
                    return SecondFragment.newInstance(position);
                case 2:
                    return ThirdFragment.newInstance(position);
                case 3:
                    return FourthFragment.newInstance(position);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


    public static void mRecreate(){
        mainActivity.recreate();
    }
}
