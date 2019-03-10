package slabko.vladislav.slabkovladlauncher.containers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import slabko.vladislav.slabkovladlauncher.AppListActivity;
import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.additional.AppInfo;
import slabko.vladislav.slabkovladlauncher.global.Constants;

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static Context mContext;


    public ListFragment() {
        // Required empty public constructor
    }


    public static ListFragment newInstance(Context c) {
        ListFragment.mContext = c;
        ListFragment pageFragment = new ListFragment();
        Bundle arguments = new Bundle();
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.my_view);
        recyclerView.setHasFixedSize(true);
        registerForContextMenu(recyclerView);

        layoutManager = new LinearLayoutManager((getActivity()));
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyListAdapter((getActivity()), inflater, Constants.apps);
        recyclerView.setAdapter(mAdapter);
        return v;
    }

    void mStartActivity(Intent intent){
        mContext.startActivity(intent);
    }
}
