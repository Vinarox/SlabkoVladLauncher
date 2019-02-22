package slabko.vladislav.slabkovladlauncher;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.NavigationView;


import android.support.v14.preference.PreferenceFragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import slabko.vladislav.slabkovladlauncher.containers.GridFragment;
import slabko.vladislav.slabkovladlauncher.containers.ListFragment;

public class AppListActivity extends AppCompatActivity
        implements GridFragment.OnFragmentInteractionListener,
        ListFragment.OnFragmentInteractionListener,
        PreferenceFragment.OnPreferenceStartFragmentCallback {

    private Fragment fragment1;
    private Fragment fragment2;
    private Fragment fragment3;
    private DrawerLayout mDrawerLayout;
    String[] str;
    private CharSequence title;
    private final String TITLE_TAG = "settingsActivityTitle";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        str = getIntent().getStringExtra("number").split(" ");

        final PackageManager pm = getPackageManager();
        //List<AppInfo> apps = new ArrayList<>();
        final List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);


        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragment1 = GridFragment.newInstance(this, str, packages);
        //fragment2 = ListFragment.newInstance(this);
        fragmentTransaction.add(R.id.content_frame, fragment1);
        fragmentTransaction.commit();

       Toolbar toolbar = findViewById(R.id.toolbar);
       setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        mDrawerLayout.closeDrawers();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        switch (menuItem.getItemId()) {
                            case R.id.nav_list:
                                fragment2 = ListFragment.newInstance(AppListActivity.this);
                                if (fragment2 != null) {
                                    transaction.replace(R.id.content_frame, fragment2);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                                break;
                            case R.id.nav_grid:
                                fragment1 = GridFragment.newInstance(AppListActivity.this, str, packages);
                                if (fragment1 != null) {
                                    transaction.replace(R.id.content_frame, fragment1);
                                    transaction.addToBackStack(null);
                                    transaction.commit();
                                }
                                break;
                            case R.id.nav_settings:
                                if (savedInstanceState == null) {
                                    transaction.replace(R.id.content_frame, (new AppListActivity.SettingsFragment()))
                                            .commit();

                                   /* transaction.replace(R.id.content_frame, (new AppListActivity.SettingsFragment()))
                                            .addToBackStack(null)
                                            .commit();*/
                                } else {
                                    title = savedInstanceState.getCharSequence(TITLE_TAG);
                                }
                                /*supportFragmentManager.addOnBackStackChangedListener {
                                if (supportFragmentManager.backStackEntryCount == 0) {
                                    setTitle(R.string.title)
                                }*/
                                //}
                            //supportActionBar?.setDisplayHomeAsUpEnabled(true)
                                break;
                            default:
                                break;
                        }
                        return true;
                    }
                });
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

    @Override
    public void onFragmentInteraction(Uri uri) {
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
}
