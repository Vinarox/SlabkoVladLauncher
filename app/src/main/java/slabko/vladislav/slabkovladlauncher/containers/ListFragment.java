package slabko.vladislav.slabkovladlauncher.containers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import slabko.vladislav.slabkovladlauncher.AppListActivity;
import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.additional.AppInfo;
import slabko.vladislav.slabkovladlauncher.global.Constants;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_FIRST_USER;
import static android.app.Activity.RESULT_OK;

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private static RecyclerView.Adapter mAdapter;
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
        //registerForContextMenu(recyclerView);

        layoutManager = new LinearLayoutManager((getActivity()));
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyListAdapter((getActivity()), inflater, Constants.apps,
                ((AppListActivity)getActivity()).getMenuInflater());
        recyclerView.setAdapter(mAdapter);
        return v;
    }

    void mStartActivity(Intent intent){
        mContext.startActivity(intent);
    }

    public static void notifyMe(Intent intent, int needDelete) {
        switch (needDelete){
            case 1:
                ((Activity)mContext).startActivityForResult(intent, 1);
                break;
        }
        notifyAllData();
    }

    public static void notifyAllData(){
        mAdapter.notifyDataSetChanged();
    }

    public static void notifyAdd(int position){
        mAdapter.notifyDataSetChanged();
    }

    public static void startToast(String str){
        Toast toast = Toast.makeText(mContext,
                str, Toast.LENGTH_SHORT);
        toast.show();
    }
}
