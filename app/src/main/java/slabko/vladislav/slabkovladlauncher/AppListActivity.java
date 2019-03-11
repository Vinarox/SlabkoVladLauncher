package slabko.vladislav.slabkovladlauncher;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.design.widget.NavigationView;


import android.support.v14.preference.PreferenceFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import java.util.List;
import java.util.concurrent.ExecutionException;

import slabko.vladislav.slabkovladlauncher.AsyncTasks.DeleteControlReceiver;
import slabko.vladislav.slabkovladlauncher.containers.Desktop;
import slabko.vladislav.slabkovladlauncher.containers.GridFragment;
import slabko.vladislav.slabkovladlauncher.containers.ListFragment;

public class AppListActivity extends AppCompatActivity
        implements PreferenceFragment.OnPreferenceStartFragmentCallback {

    FragmentTransaction transaction;
    private Fragment fragmentGrid;
    private Fragment fragmentList;
    private Fragment fragmentDesktop;
    private DrawerLayout mDrawerLayout;
    String[] str;
    private CharSequence title;
    private final String TITLE_TAG = "settingsActivityTitle";
    private DeleteControlReceiver deleteControlReceiver = new DeleteControlReceiver();

    public void mStartActivity(Intent intent){
        startActivity(intent);
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        str = getIntent().getStringExtra("number").split(" ");

        this.registerReceiver(deleteControlReceiver, new IntentFilter("android.intent.action.TIME_TICK"));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragmentList = ListFragment.newInstance(AppListActivity.this);
        transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, fragmentList);
        transaction.commit();


        fragmentGrid = GridFragment.newInstance(AppListActivity.this, str);
        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragmentGrid);
        transaction.commit();


        transaction = getFragmentManager().beginTransaction();
        fragmentDesktop = Desktop.newInstance(this);
        transaction.replace(R.id.content_frame, fragmentDesktop);
        transaction.commit();



        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        transaction = getFragmentManager().beginTransaction();
                        switch (menuItem.getItemId()) {
                            case R.id.nav_desk:
                                fragmentDesktop = Desktop.newInstance(AppListActivity.this);
                                if (fragmentDesktop != null) {
                                    transaction.replace(R.id.content_frame, fragmentDesktop);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                                break;
                            case R.id.nav_list:
                                fragmentList = ListFragment.newInstance(AppListActivity.this);
                                if (fragmentList != null) {
                                    transaction.replace(R.id.content_frame, fragmentList);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                                break;
                            case R.id.nav_grid:
                                fragmentGrid = GridFragment.newInstance(AppListActivity.this, str);
                                if (fragmentGrid != null) {
                                    transaction.replace(R.id.content_frame, fragmentGrid);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                                break;
                            case R.id.nav_settings:
                                if (savedInstanceState == null) {
                                    transaction.replace(R.id.content_frame, (new AppListActivity.SettingsFragment()))
                                            .addToBackStack(null)
                                            .commit();
                                } else {
                                    title = savedInstanceState.getCharSequence(TITLE_TAG);
                                }
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
    }

    class MyActivityTouhcListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
        public boolean onSwipeRight() {
            return true;
        }
        public boolean onSwipeLeft() {
            return true;
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    public void myImageClick(View view) {
        final Intent intent = new Intent();
        intent.setClass(AppListActivity.this, ProfileActivity.class);
        startActivity(intent);
        /*mDrawerLayout.closeDrawers();
        if (behavior.getState() != BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }*/
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save current activity title so we can set it again after a configuration change
        outState.putCharSequence(TITLE_TAG, title);
    }

    @Override
    public boolean onSupportNavigateUp(){
        if (getFragmentManager().popBackStackImmediate()) {
            return true;
        }
        return super.onSupportNavigateUp();
    }



    @Override
    public boolean onPreferenceStartFragment(PreferenceFragment caller, Preference preference) {
        Bundle args = preference.getExtras();
        FragmentManager fragmentFactory = getFragmentManager();
        Fragment fragment = Fragment.instantiate(this, preference.getFragment(), args);
        fragment.setArguments(args);
        fragment.setTargetFragment(caller, 0);
        getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .addToBackStack(null)
                .commit();
        CharSequence title = preference.getTitle();
        return true;
    }


    public static class SettingsFragment extends PreferenceFragment {
       /* @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settings);
        }*/

        @Override
        public void onCreatePreferences(Bundle bundle, String s) {
            setPreferencesFromResource(R.xml.settings, s);
        }
    }


    public static class SettingsSort extends PreferenceFragment{
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            // Load the preferences from an XML resource
            setPreferencesFromResource(R.xml.sort, rootKey);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(deleteControlReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
