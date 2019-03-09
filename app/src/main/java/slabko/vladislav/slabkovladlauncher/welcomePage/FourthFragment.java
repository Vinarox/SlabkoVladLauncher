package slabko.vladislav.slabkovladlauncher.welcomePage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import slabko.vladislav.slabkovladlauncher.AppListActivity;
import slabko.vladislav.slabkovladlauncher.R;

public class FourthFragment extends Fragment {
    private RadioButton thirdLayoutRB_1;
    private RadioButton thirdLayoutRB_2;
    private String params = "4 6";

    public FourthFragment() {
        // Required empty public constructor
    }

    public static FourthFragment newInstance(int page) {
        FourthFragment pageFragment = new FourthFragment();
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
        View v = inflater.inflate(R.layout.fragment_fourth, container, false);

        final View view = v.findViewById(R.id.button4);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final Intent intent = new Intent();
                intent.setClass(v.getContext(), AppListActivity.class);
                intent.putExtra("number", params);
                startActivity(intent);
            }
        });

        thirdLayoutRB_1 = v.findViewById(R.id.radioButton3);
        thirdLayoutRB_1.setOnClickListener(radioButtonClickListener);

        thirdLayoutRB_2 = v.findViewById(R.id.radioButton4);
        thirdLayoutRB_2.setOnClickListener(radioButtonClickListener);
        return v;
    }

        View.OnClickListener radioButtonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.radioButton3:
                    rb.setChecked(true);
                    thirdLayoutRB_2.setChecked(false);
                    params = new String("4 6");
                    break;
                case R.id.radioButton4:
                    rb.setChecked(true);
                    thirdLayoutRB_1.setChecked(false);
                    params = new String("5 7");
                    break;
                default:
                    break;
            }
        }
    };
}
