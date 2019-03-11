package slabko.vladislav.slabkovladlauncher.containers;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;
import java.util.Map;

import slabko.vladislav.slabkovladlauncher.AppListActivity;
import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.additional.AppInfo;
import slabko.vladislav.slabkovladlauncher.global.Constants;

public class GridFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String[] str = {"4", "6"};
    private static Context mContext;


    public GridFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(Context c, String[] str) {
        GridFragment.mContext = c;
        GridFragment.str = str;
        GridFragment pageFragment = new GridFragment();
        Bundle arguments = new Bundle();
        pageFragment.setArguments(arguments);
        return pageFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_grid, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.my_recycler_view);
        //registerForContextMenu(mRecyclerView);

        //str = ((AppListActivity)getActivity()).getIntent().getStringExtra("number").split(" ");
        int numbCol = 0;
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            numbCol = Integer.parseInt(str[0]);
        }
        else {
            numbCol = Integer.parseInt(str[1]);
        }
        mLayoutManager = new GridLayoutManager((getActivity()), numbCol);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new RecycleAdapter((getActivity()), numbCol, Constants.apps, inflater,
                ((AppListActivity)getActivity()).getMenuInflater());
        mRecyclerView.setAdapter(mAdapter);

        return v;
    }

    public static void notifyMe() {
                mAdapter.notifyDataSetChanged();
    }
}
