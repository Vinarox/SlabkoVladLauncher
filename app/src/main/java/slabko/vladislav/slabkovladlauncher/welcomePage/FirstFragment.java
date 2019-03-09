package slabko.vladislav.slabkovladlauncher.welcomePage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import slabko.vladislav.slabkovladlauncher.MainActivity;
import slabko.vladislav.slabkovladlauncher.R;

public class FirstFragment extends Fragment {
    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance(int page) {
        FirstFragment pageFragment = new FirstFragment();
        Bundle arguments = new Bundle();
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        final View view = v.findViewById(R.id.button);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ((MainActivity)getActivity()).selectPage(1);
            }
        });
        return v;
    }
}
