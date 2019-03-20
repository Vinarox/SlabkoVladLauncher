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
import android.widget.RadioButton;
import android.widget.RadioGroup;

import slabko.vladislav.slabkovladlauncher.MainActivity;
import slabko.vladislav.slabkovladlauncher.PreStartActivity;
import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.global.Constants;

public class ThirdFragment extends Fragment {
    private static SharedPreferences sharedPreferences;
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

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Constants.appContext);

        final View view = v.findViewById(R.id.button3);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                ((MainActivity)getActivity()).selectPage(3);
            }
        });

        RadioGroup radioGroup = (RadioGroup) v.findViewById(R.id.radio1);
        RadioButton rbThemeLight = v.findViewById(R.id.radioButton1);
        RadioButton rbThemeDark = v.findViewById(R.id.radioButton2);
        if (Constants.THEME_CURRENT_ID == Constants.THEME_DARK_ID) {
            rbThemeDark.setChecked(true);
        } else
            rbThemeLight.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radioButton1:
                     sharedPreferences
                                .edit()
                                .putString(Constants.KEY_THEME, Constants.THEME_LIGHT)
                                .apply();
                        break;
                    case R.id.radioButton2:
                        sharedPreferences
                                .edit()
                                .putString(Constants.KEY_THEME, Constants.THEME_DARK)
                                .apply();
                        break;
                    default:
                        break;
                }
            }
        });
        return v;
    }
}
