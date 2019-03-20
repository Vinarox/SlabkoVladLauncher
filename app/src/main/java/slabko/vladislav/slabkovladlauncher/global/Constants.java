package slabko.vladislav.slabkovladlauncher.global;

import android.content.Context;
import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.additional.AppInfo;

public class Constants {
    public static Context appContext;

    public static final int THEME_DEFAULT_ID = R.style.AppTheme;
    public static final String MODEL_DEFAULT = "default";
    public static final String MODEL_TIGHT = "tight";
    public static final String SORT_DEFAULT = "no_sort";

    public static final String KEY_THEME = "pref_theme";
    public static final String KEY_MODEL = "pref_model";
    public static final String KEY_SORT = "sort";
    public static final String KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD = "pref_show_welcome_page_on_next_load";

    public static final String THEME_LIGHT = "AppTheme";
    public static final String THEME_DARK = "MyTheme";
    public static final int THEME_DARK_ID = R.style.MyTheme;
    public static final int THEME_LIGHT_ID = R.style.AppTheme;

    public static int screenWidth;
    public static int screenHight;
    public static AppInfo apps;

    public static final String SETTINGS_THEME = "list_theme";
    public static final String SETTINGS_SORT = "sort";

    public static int THEME_CURRENT_ID = R.style.AppTheme;
    public static String MODEL_CURRENT = "default";
    public static String SORT_CURRENT = "no_sort";
    public static boolean SHOW_WELCOME_PAGE = true;

    public static final String SETTINGS_MODEL = "list_maquette";

    public static final String NO_SORT = "no_sort";
    public static final String DATE_SORT = "date_sort";
    public static final String AZ_SORT = "AZ_sort";
    public static final String ZA_SORT = "ZA_sort";

    public static final String API_KEY = "0d309191-768e-409f-b4ad-0e56fd386ed2";

    public  Constants(int x, int y){
        screenWidth = x;
        screenHight = y;
    }
}
