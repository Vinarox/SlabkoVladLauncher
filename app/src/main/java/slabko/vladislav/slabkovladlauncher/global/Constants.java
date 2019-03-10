package slabko.vladislav.slabkovladlauncher.global;

import slabko.vladislav.slabkovladlauncher.R;
import slabko.vladislav.slabkovladlauncher.additional.AppInfo;

public class Constants {
    public static final int THEME_DEFAULT_ID = R.style.AppTheme;
    public static final String KEY_THEME = "pref_theme";
    public static final String KEY_MODEL = "pref_model";
    public static final String KEY_SORT = "pref_sort";
    public static final String KEY_SHOW_WELCOME_PAGE_ON_NEXT_LOAD = "pref_show_welcome_page_on_next_load";
    public static final String MODEL_DEFAULT = "default";
    public static final String SORT_DEFAULT = "no_sort";
    public static final String THEME_LIGHT = "AppTheme";
    public static final String THEME_DARK = "MyTheme";
    public static final int THEME_DARK_ID = R.style.MyTheme;
    public static final int THEME_LIGHT_ID = R.style.AppTheme;

    public static int screenWidth;
    public static int screenHight;
    public static AppInfo apps;

    public  Constants(int x, int y){
        screenWidth = x;
        screenHight = y;
    }
}
