package slabko.vladislav.slabkovladlauncher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import slabko.vladislav.slabkovladlauncher.welcomePage.FirstFragment;
import slabko.vladislav.slabkovladlauncher.welcomePage.FourthFragment;
import slabko.vladislav.slabkovladlauncher.welcomePage.SecondFragment;
import slabko.vladislav.slabkovladlauncher.welcomePage.ThirdFragment;

public class MainActivity extends AppCompatActivity
        implements FirstFragment.OnFragmentInteractionListener,
        SecondFragment.OnFragmentInteractionListener,
        ThirdFragment.OnFragmentInteractionListener,
        FourthFragment.OnFragmentInteractionListener {

    ViewPager pager;
    PagerAdapter pagerAdapter;
    String[] titles = {"App Name", "App Description", "Background", "Scheme"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager = (ViewPager) findViewById(R.id.pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
    }

    public void selectPage(int page) {
        pager.setCurrentItem(page);
    }

    public void setTheme(String m_theme) {
        final int themeID = this.getResources().getIdentifier(m_theme,
                "style", this.getPackageName());
        //getTheme().applyStyle(R.style.MyTheme, true);
        super.setTheme(R.style.MyTheme);
        //this.finish();
        //this.startActivity(new Intent(this, MainActivity.class));
        //pager.setCurrentItem(2);
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
    public void onFragmentInteraction(Uri uri) {
        System.out.println();
    }
}
