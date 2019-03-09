package slabko.vladislav.slabkovladlauncher.welcomePage;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import slabko.vladislav.slabkovladlauncher.MainActivity;
import slabko.vladislav.slabkovladlauncher.R;

public class ThirdFragment extends Fragment {
    public ThirdFragment() {
        // Required empty public constructor
    }

    public static ThirdFragment newInstance(int page) {
        ThirdFragment pageFragment = new ThirdFragment();
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
        View v = inflater.inflate(R.layout.fragment_third, container, false);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences((MainActivity)getContext());

        final View view = v.findViewById(R.id.button3);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ((MainActivity)getActivity()).selectPage(3);
            }
        });

        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radio1);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                     /*   sharedPreferences
                                .edit()
                                .putString(PreferenceConstants.KEY_THEME, PreferenceConstants.THEME_LIGHT)
                                .apply();*/
                        ((MainActivity)getActivity()).setMyTheme(1);
                        break;
                    case R.id.radioButton2:
                        ((MainActivity)getActivity()).setMyTheme(2);
                        break;
                    default:
                        break;
                }
            }
        });
        return v;
    }
}
