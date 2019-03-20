package slabko.vladislav.slabkovladlauncher.containers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import slabko.vladislav.slabkovladlauncher.AppListActivity;
import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.additional.AppInfo;
import slabko.vladislav.slabkovladlauncher.global.Constants;

public class GridFragment extends Fragment {
    private static RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private static RecyclerView.LayoutManager mLayoutManager;
    private static int[] str = {4, 6};
    private static Context mContext;
    private static Activity curActivity;
    private SharedPreferences sharedPreferences;
    private static LayoutInflater curInflater;
    private static GridFragment gridFragment;


    public GridFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Context c) {
        GridFragment.mContext = c;
        GridFragment pageFragment = new GridFragment();
        Bundle arguments = new Bundle();
        pageFragment.setArguments(arguments);
        return pageFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gridFragment = this;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Constants.appContext);
        switch (sharedPreferences.getString(Constants.KEY_MODEL, Constants.MODEL_DEFAULT)){
            case Constants.MODEL_DEFAULT:
                str[0] = 4;
                str[1] = 6;
                break;
            case Constants.MODEL_TIGHT:
                str[0] = 5;
                str[1] = 7;
                break;
        }

        View v = inflater.inflate(R.layout.fragment_grid, container, false);
        curActivity = getActivity();
        curInflater = inflater;

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        //registerForContextMenu(mRecyclerView);

        int numbCol = 0;
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            numbCol = str[0];
        }
        else {
            numbCol = str[1];
        }
        mLayoutManager = new GridLayoutManager((getActivity()), numbCol);
        //mLayoutManager.
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleAdapter((getActivity()), numbCol, Constants.apps, inflater,
                ((AppListActivity)getActivity()).getMenuInflater());
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }


    public static void changeGridLayout(String model){
        if(curActivity != null) {
        int colNumb;
        if(Constants.appContext.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            if (model.equals(Constants.MODEL_DEFAULT))
                colNumb = 4;
            else
                colNumb = 5;
        } else {
            if (model.equals(Constants.MODEL_DEFAULT))
                colNumb = 6;
            else
                colNumb = 7;
        }

            mLayoutManager = new GridLayoutManager(curActivity, colNumb);
            mRecyclerView.setLayoutManager(mLayoutManager);
            //mAdapter = new RecycleAdapter(curActivity, colNumb, Constants.apps, curInflater,
            //    ((AppListActivity)curActivity).getMenuInflater());
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
    }

    public static void notifyMe(Intent intent, int needDelete) {
        switch (needDelete){
            case 1:
                mContext.startActivity(intent);
                break;
        }
        notifyAllData();
    }

    public static void notifyAllData(){
        mAdapter.notifyDataSetChanged();
    }

    public static void notifyAdd(int position){
        mAdapter.notifyItemInserted(position);
    }

    public static void startToast(String str){
        Toast toast = Toast.makeText(mContext,
                str, Toast.LENGTH_SHORT);
        toast.show();
    }
}
